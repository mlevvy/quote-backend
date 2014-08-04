package pl.newit.quote.sentence.dto

import org.joda.time.DateTime

import play.api.libs.json.Json

object SentenceInputExample {
  val Equality = SentenceInput(
    forDay = new DateTime("2014-02-01T00:00:00.000Z"),
    content = "Before God we are all equally wise - and equally foolish.",
    authorId = "Albert Einstein")

  val EqualityJson = Json.obj(
    "forDay" -> 1391212800000L,
    "content" -> "Before God we are all equally wise - and equally foolish.",
    "authorId" -> "Albert Einstein")
}