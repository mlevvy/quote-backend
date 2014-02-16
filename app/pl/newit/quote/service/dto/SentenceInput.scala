package pl.newit.quote.service.dto

import org.joda.time.DateTime

import play.api.libs.json._

final case class SentenceInput(forDay: DateTime, content: String)

object SentenceInput {
  implicit val reads = Json.reads[SentenceInput]
}