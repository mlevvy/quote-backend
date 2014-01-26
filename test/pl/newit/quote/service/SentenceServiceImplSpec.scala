package pl.newit.quote.service
import scala.concurrent.ExecutionContext.Implicits.global

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Interval
import org.mockito.Mockito.reset
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import pl.newit.common.time.TimeSource
import pl.newit.quote.author.dao.AuthorDao
import pl.newit.quote.author.dto.Author
import pl.newit.quote.common.Audit
import pl.newit.quote.sentence.dao.SentenceDao
import pl.newit.quote.sentence.dto.Sentence
import pl.newit.quote.service.dto.AuthorInfo
import pl.newit.quote.service.dto.SentenceInfo
import pl.newit.test.concurrent._
import play.api.libs.iteratee.Enumerator

class SentenceServiceImplSpec extends Specification with Mockito {

  val sentences = mock[SentenceDao]
  val authors = mock[AuthorDao]
  val clock = mock[TimeSource]

  val audit = Audit(
    creatorId = "creator",
    updaterId = "updater",
    create = new DateTime(),
    update = new DateTime())

  val dummySentences = List(
    Sentence(
      id = "s1",
      forDay = new DateTime("2014-02-01T00:00:00.000Z"),
      content = "Lorem ipsum",
      authorId = "a1",
      audit = audit),
    Sentence(
      id = "s2",
      forDay = new DateTime("2014-02-02T00:00:00.000Z"),
      content = "dolor sit amet",
      authorId = "a1",
      audit = audit),
    Sentence(
      id = "s3",
      forDay = new DateTime("2014-02-03T00:00:00.000Z"),
      content = "Lorem ipsum",
      authorId = "a2",
      audit = audit))

  val dummyAuthors = List(
    Author(
      id = "a1",
      displayName = "Baz Faz",
      audit = audit),
    Author(
      id = "a2",
      displayName = "Foo Bar",
      audit = audit))

  override def before() = reset(sentences, authors, clock)

  "getAll" should {
    "ask for sentences between since and now" in {
      sentences.getAll(any) returns Enumerator()
      authors.getAll(any) returns Enumerator()
      clock.now(any) returns new DateTime("2014-01-24T22:15:50.981+01:00")

      result {
        new SentenceServiceImpl(sentences, authors, clock)
          .getAll(new DateTime("2013-02-25T23:16:51.982+01:00"))
      } === Nil

      there was {
        one(sentences).getAll(new Interval(
          new DateTime("2013-02-25T23:16:51.982+01:00"),
          new DateTime("2014-01-24T22:15:50.981+01:00")))
        noMoreCallsTo(sentences)
      }
    }

    "ask for current time in since related time zone" in {
      sentences.getAll(any) returns Enumerator()
      authors.getAll(any) returns Enumerator()
      clock.now(any) returns new DateTime("2014-01-24T22:15:50.981Z", DateTimeZone.UTC)

      result {
        new SentenceServiceImpl(sentences, authors, clock)
          .getAll(new DateTime("2013-02-25T23:16:51.982Z", DateTimeZone.UTC))
      } === Nil

      there was {
        one(clock).now(DateTimeZone.UTC)
        noMoreCallsTo(clock)
      }
    }

    "ask for authors according to received sentences" in {
      sentences.getAll(any) returns Enumerator.enumerate(dummySentences)
      authors.getAll(any) returns Enumerator()
      clock.now(any) returns new DateTime()

      result {
        new SentenceServiceImpl(sentences, authors, clock)
          .getAll(new DateTime("2012-01-01T00:00:00.000+01:00"))
      } === Nil

      there was {
        one(authors).getAll(List("a1", "a1", "a2"))
        noMoreCallsTo(authors)
      }
    }

    "ignore sentences from unknown authors" in {
      sentences.getAll(any) returns Enumerator.enumerate(dummySentences)
      authors.getAll(any) returns Enumerator.enumerate(dummyAuthors.filterNot(_.id == "a1"))
      clock.now(any) returns new DateTime()

      result {
        new SentenceServiceImpl(sentences, authors, clock)
          .getAll(new DateTime("2012-01-01T00:00:00.000+01:00"))
      } === List(SentenceInfo(
        id = "s3",
        content = "Lorem ipsum",
        author = AuthorInfo(
          id = "a2",
          displayName = "Foo Bar")))
    }
  }
}