package pl.newit.quote.service
import scala.concurrent.Future

import org.joda.time.DateTime

import pl.newit.quote.sentence.dto.{Sentence, SentenceInput}
import pl.newit.quote.service.dto.{SentenceUpdate, SentenceInfo}

trait SentenceService {
  /** Creates a new sentence. Returns None if author does not exist. */
  def create(from: SentenceInput): Future[Option[SentenceInfo]]

  /** Gets all the sentences from since to now. */
  def getAll(since: DateTime): Future[List[SentenceInfo]]

  /** Updates the sentence content. */
  def update(sentenceId: SentenceInput, contentId: String): Future[Option[Sentence]]
}