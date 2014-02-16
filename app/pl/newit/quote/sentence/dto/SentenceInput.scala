package pl.newit.quote.sentence.dto

import org.joda.time.DateTime

final case class SentenceInput(
  forDay: DateTime,
  content: String,
  authorId: String)
