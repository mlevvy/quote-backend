package pl.newit.quote.sentence.dto

import org.joda.time.DateTime

import pl.newit.quote.common.Audits

object Sentences {
  val Equality = Sentence(
    id = "s1",
    forDay = new DateTime("2014-02-01T00:00:00.000Z"),
    content = "Before God we are all equally wise - and equally foolish.",
    authorId = "a1",
    audit = Audits.audit)

  val Imagination = Sentence(
    id = "s2",
    forDay = new DateTime("2014-02-02T00:00:00.000Z"),
    content = "Imagination is more important than knowledge...",
    authorId = "a1",
    audit = Audits.audit)

  val Woman = Sentence(
    id = "s3",
    forDay = new DateTime("2014-02-03T00:00:00.000Z"),
    content = "Every man's dream is to be able to sink into the arms of a woman without also falling into her hands.",
    authorId = "a2",
    audit = Audits.audit)
}