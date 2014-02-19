package pl.newit.quote.service.dto

import org.joda.time.DateTime

import play.api.libs.json._

final case class SentencePartialInput(forDay: DateTime, content: String)

object SentencePartialInput {
  implicit val reads = Json.reads[SentencePartialInput]
}