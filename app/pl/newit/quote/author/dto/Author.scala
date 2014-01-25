package pl.newit.quote.author.dto
import pl.newit.quote.common.Audit

import play.api.libs.json._

final case class Author(
  id: String,
  displayName: String,
  audit: Audit)

object Author {
  implicit val format = Json.format[Author]
}