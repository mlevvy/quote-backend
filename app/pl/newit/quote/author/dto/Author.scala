package pl.newit.quote.author.dto
import pl.newit.quote.common.Audit

import play.api.libs.functional.syntax._
import play.api.libs.json._

final case class Author(
  id: String,
  displayName: String,
  audit: Audit)

object Author {
  implicit val format: Format[Author] = (
    (__ \ '_id).format[String] ~
    (__ \ 'displayName).format[String] ~
    (__ \ 'audit).format[Audit])(
      Author.apply, unlift(Author.unapply _))
}