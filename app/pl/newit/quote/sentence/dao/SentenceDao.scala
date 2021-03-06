package pl.newit.quote.sentence.dao

import pl.newit.quote.service.dto.SentenceUpdate

import scala.concurrent.Future

import org.joda.time.Interval

import pl.newit.quote.sentence.dto.Sentence
import pl.newit.quote.sentence.dto.SentenceInput
import play.api.libs.iteratee.Enumerator

trait SentenceDao {
  def create(from: SentenceInput, creatorId: String): Future[Sentence]

  def getAll(interval: Interval): Enumerator[Sentence]

  def delete(id: String): Future[Boolean]

  def update(id: String, newContent: SentenceUpdate): Future[Option[SentenceUpdate]]
}