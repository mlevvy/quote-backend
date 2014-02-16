package pl.newit.quote.service.dto

import org.joda.time.DateTime

import play.api.libs.json.Json

object SentencePartialInputExample {
  val Equality = SentencePartialInput(
    forDay = new DateTime("2014-02-01T00:00:00.000Z"),
    content = "Before God we are all equally wise - and equally foolish.")

  val EqualityJson = Json.obj(
    "forDay" -> 1391212800000L,
    "content" -> "Before God we are all equally wise - and equally foolish.")
}