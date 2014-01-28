package pl.newit.quote.sentence.dao
import scala.concurrent.ExecutionContext.Implicits.global

import org.joda.time.Interval

import com.google.inject.Inject
import com.google.inject.name.Named

import pl.newit.common.id.UniqueIdGenerator
import pl.newit.common.mongo.json._
import pl.newit.common.time.TimeSource
import pl.newit.quote.common.Audit
import pl.newit.quote.sentence.dto.Sentence
import pl.newit.quote.sentence.dto.SentenceInput
import play.api.libs.json._
import play.modules.reactivemongo.json.collection.JSONCollection

private[dao] class SentenceDaoImpl @Inject() (@Named("Sentence") collection: JSONCollection, clock: TimeSource,
  generator: UniqueIdGenerator) extends SentenceDao {

  def audit(creatorId: String): Audit =
    Audit.valueOf(creatorId, clock.now())

  def create(sentence: Sentence) =
    collection.insert(sentence)
      .map(_ => sentence)

  override def create(from: SentenceInput, creatorId: String) =
    create(Sentence(
      id = generator.generate(),
      forDay = from.forDay,
      content = from.content,
      authorId = from.authorId,
      audit = audit(creatorId)))

  override def getAll(interval: Interval) =
    collection.find(
      Json.obj("forDay" -> Json.obj(
        "$gte" -> interval.getStart,
        "$lt" -> interval.getEnd)))
      .cursor[Sentence]
      .enumerate()

  override def delete(id: String) =
    collection.remove(
      Json.obj("_id" -> id))
      .map(_.n > 0)

}