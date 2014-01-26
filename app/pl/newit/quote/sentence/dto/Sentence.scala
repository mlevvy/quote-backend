package pl.newit.quote.sentence.dto
import org.joda.time.DateTime

import pl.newit.common.mongo.json._
import pl.newit.quote.common.Audit
import play.api.libs.functional.syntax._
import play.api.libs.json._

final case class Sentence(
  id: String,
  forDay: DateTime,
  content: String,
  authorId: String,
  audit: Audit)

object Sentence {
  implicit val format: Format[Sentence] = (
    (__ \ '_id).format[String] ~
    (__ \ 'forDay).format[DateTime] ~
    (__ \ 'content).format[String] ~
    (__ \ 'authorId).format[String] ~
    (__ \ 'audit).format[Audit])(
      Sentence.apply, unlift(Sentence.unapply _))
}