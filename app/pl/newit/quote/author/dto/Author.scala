package pl.newit.quote.author.dto

import play.api.libs.json._
import pl.newit.quote.common.Audit

final case class Author(id: String, displayName: String, audit: Audit)

object Author {
  implicit val format = Json.format[Author]
}