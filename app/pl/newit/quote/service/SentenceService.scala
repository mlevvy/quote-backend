package pl.newit.quote.service
import scala.concurrent.Future

import org.joda.time.DateTime

import pl.newit.quote.service.dto.SentenceInfo

trait SentenceService {
  def getAll(since: DateTime): Future[List[SentenceInfo]]
}