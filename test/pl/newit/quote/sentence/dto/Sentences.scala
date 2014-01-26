package pl.newit.quote.sentence.dto

import org.joda.time.DateTime

import pl.newit.quote.common.Audits

object Sentences {
  val sentence1 = Sentence(
    id = "s1",
    forDay = new DateTime("2014-02-01T00:00:00.000Z"),
    content = "Lorem ipsum",
    authorId = "a1",
    audit = Audits.audit)

  val sentence2 = Sentence(
    id = "s2",
    forDay = new DateTime("2014-02-02T00:00:00.000Z"),
    content = "dolor sit amet",
    authorId = "a1",
    audit = Audits.audit)

  val sentence3 = Sentence(
    id = "s3",
    forDay = new DateTime("2014-02-03T00:00:00.000Z"),
    content = "consectetur adipisicing elit",
    authorId = "a2",
    audit = Audits.audit)
}