package pl.newit.quote.sentence.dao
import org.joda.time.Interval

import pl.newit.quote.sentence.dto.Sentence
import play.api.libs.iteratee.Enumerator

trait SentenceDao {
  def getAll(interval: Interval): Enumerator[Sentence]
}