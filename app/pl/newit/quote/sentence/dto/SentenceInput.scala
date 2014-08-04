package pl.newit.quote.sentence.dto

import org.joda.time.DateTime

import play.api.libs.json.Json

final case class SentenceInput(
  forDay: DateTime,
  content: String,
  authorId: String)

object SentenceInput {
  implicit val reads = Json.reads[SentenceInput]
}