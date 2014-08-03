package pl.newit.quote.service.dto

import play.api.libs.json.Json

final case class SentenceUpdate(content: String)

object SentenceUpdate {
  implicit val format = Json.format[SentenceUpdate]
}