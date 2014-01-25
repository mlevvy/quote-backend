package pl.newit.quote.sentence.dto
import org.joda.time.DateTime

import pl.newit.common.mongo.json._
import pl.newit.quote.common.Audit
import play.api.libs.json._

final case class Sentence(
  id: String,
  forDay: DateTime,
  content: String,
  authorId: String,
  audit: Audit)

object Sentence {
  implicit val format = Json.format[Sentence]
}