package pl.newit.quote.sentence.dao
import scala.concurrent.ExecutionContext.Implicits.global

import org.joda.time.Interval

import com.google.inject.Inject
import com.google.inject.name.Named

import pl.newit.common.mongo.json._
import pl.newit.quote.sentence.dto.Sentence
import play.api.libs.json._
import play.modules.reactivemongo.json.collection.JSONCollection

private[dao] class SentenceDaoImpl @Inject() (@Named("Sentence") collection: JSONCollection) extends SentenceDao {
  override def getAll(interval: Interval) =
    collection.find(
      Json.obj("forDay" -> Json.obj(
        "$gte" -> interval.getStart,
        "$lt" -> interval.getEnd)))
      .cursor[Sentence]
      .enumerate()
}