package pl.newit.quote.service.dto

import play.api.libs.json.Json

object SentenceUpdateInputExample {
  val CorrectUpdate = Json.obj(
    "content" -> "New Content")
}