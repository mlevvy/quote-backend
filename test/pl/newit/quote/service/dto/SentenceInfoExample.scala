package pl.newit.quote.service.dto

import org.joda.time.DateTime

object SentenceInfoExample {
  val Equality = SentenceInfo(
    id = "s1",
    forDay = new DateTime("2014-02-01T00:00:00.000Z"),
    content = "Before God we are all equally wise - and equally foolish.",
    author = AuthorInfoExample.AlbertEinstein)

  val Imagination = SentenceInfo(
    id = "s2",
    forDay = new DateTime("2014-02-02T00:00:00.000Z"),
    content = "Imagination is more important than knowledge...",
    author = AuthorInfoExample.AlbertEinstein)

  val Woman =
    SentenceInfo(
      id = "s3",
      forDay = new DateTime("2014-02-03T00:00:00.000Z"),
      content = "Every man's dream is to be able to sink into the arms of a woman without also falling into her hands.",
      author = AuthorInfoExample.JerryLewis)
}