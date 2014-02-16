package pl.newit.quote.service
import scala.concurrent.Future

import org.joda.time.DateTime

import pl.newit.quote.service.dto.SentenceInfo
import pl.newit.quote.service.dto.SentencePartialInput

trait SentenceService {
  /** Creates a new sentence. Returns None if author does not exist. */
  def create(from: SentencePartialInput, authorId: String): Future[Option[SentenceInfo]]

  /** Gets all the sentences from since to now. */
  def getAll(since: DateTime): Future[List[SentenceInfo]]
}