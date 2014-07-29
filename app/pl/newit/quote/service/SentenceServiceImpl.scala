package pl.newit.quote.service
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Future.successful

import org.joda.time.DateTime
import org.joda.time.Interval

import com.google.inject.Inject

import pl.newit.common.time.TimeSource
import pl.newit.quote.author.dao.AuthorDao
import pl.newit.quote.author.dto.Author
import pl.newit.quote.sentence.dao.SentenceDao
import pl.newit.quote.sentence.dto.Sentence
import pl.newit.quote.sentence.dto.SentenceInput
import pl.newit.quote.service.dto.{SentenceUpdate, SentenceInfo, SentencePartialInput}
import play.api.libs.iteratee.Iteratee

private[service] class SentenceServiceImpl @Inject() (sentences: SentenceDao, authors: AuthorDao, clock: TimeSource)
  extends SentenceService {

  def transform[T, S](from: Future[Option[T]])(f: T => Future[S]) =
    from.flatMap {
      case Some(t) => f(t).map(Option(_))
      case None => successful(None)
    }

  override def create(from: SentencePartialInput, authorId: String) =
    transform(authors.get(authorId))(author =>
      sentences.create(
        from = SentenceInput(
          forDay = from.forDay,
          content = from.content,
          authorId = author.id),
        creatorId = "anonymous")
        .map(SentenceInfo.valueOf(_, author)))

  def toMap[K, V](k: V => K): Iteratee[V, Map[K, V]] =
    Iteratee.fold(Map.newBuilder[K, V])(
      (builder, v: V) => builder += k(v) -> v)
      .map(_.result())

  def join[I, O, K, V](left: List[I], right: Map[K, V])(fk: I => K)(combine: (I, V) => O) =
    left.flatMap(in =>
      right.get(fk(in))
        .map(combine(in, _)))

  def sentences(interval: Interval): Future[List[Sentence]] =
    sentences.getAll(interval) |>>> Iteratee.getChunks

  def authors(sentences: List[Sentence]): Future[Map[String, Author]] =
    authors.getAll(sentences.map(_.authorId)) |>>> toMap(_.id)

  def getAll(interval: Interval): Future[List[SentenceInfo]] =
    sentences(interval).flatMap(sentences =>
      authors(sentences).map(authors =>
        join(sentences, authors)(_.authorId)(
          SentenceInfo.valueOf)))

  def getAll(from: DateTime, to: DateTime): Future[List[SentenceInfo]] =
    if (!to.isBefore(from)) getAll(new Interval(from, to)) else successful(Nil)

  override def getAll(since: DateTime) =
    getAll(since, clock.now(since.getZone))

  override def update(sentenceUpdate: SentenceUpdate, sentenceId: String):Future[Option[SentenceUpdate]] = {
    sentences.update(sentenceId, sentenceUpdate)
  }

}