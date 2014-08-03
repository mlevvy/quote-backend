package pl.newit.quote.sentence.dao

import pl.newit.quote.service.dto.SentenceUpdate

import scala.concurrent.Future.successful

import org.joda.time.DateTime
import org.mockito.Matchers
import org.mockito.Mockito.reset
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeExample

import pl.newit.common.id.UniqueIdGenerator
import pl.newit.common.mongo.LastErrorExample
import pl.newit.common.time.TimeSource
import pl.newit.quote.common.Audit
import pl.newit.quote.sentence.dto.Sentence
import pl.newit.quote.sentence.dto.SentenceInput
import pl.newit.test.concurrent._
import play.api.libs.json.Json
import play.modules.reactivemongo.json.collection.JSONCollection

class SentenceDaoImplSpec extends Specification with Mockito with BeforeExample {

  val collection = mock[JSONCollection]
  val clock = mock[TimeSource]
  val generator = mock[UniqueIdGenerator]

  override def before() = reset(collection, clock, generator)

  "create" should {
    "insert new document into collection" in {
      collection.insert[Sentence](document = any, writeConcern = any)(
        writer = any, ec = any) returns successful(LastErrorExample.oneUpdated)

      clock.now() returns new DateTime("2004-02-12T15:19:21Z")
      generator.generate() returns "foo"

      val expected = Sentence(
        id = "foo",
        forDay = new DateTime("2005-03-13T16:20:22Z"),
        content = "Lorem ipsum",
        authorId = "a1",
        audit = Audit(
          creatorId = "user",
          updaterId = "user",
          create = new DateTime("2004-02-12T15:19:21Z"),
          update = new DateTime("2004-02-12T15:19:21Z")))

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .create(
            from = SentenceInput(
              forDay = new DateTime("2005-03-13T16:20:22Z"),
              content = "Lorem ipsum",
              authorId = "a1"),
            creatorId = "user")
      } === expected
    }
  }

  "delete" should {
    "delete document from collection" in {
      collection.remove(any, any, any)(any, any) returns
        successful(LastErrorExample.oneExistingUpdated)

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .delete("foo")
      } === true

      there was one(collection).remove(
        query = Matchers.eq(Json.obj("_id" -> "foo")),
        writeConcern = any,
        firstMatchOnly = any)(
          writer = any,
          ec = any)
    }

    "return false in case if nothing has been deleted" in {
      collection.remove(any, any, any)(any, any) returns
        successful(LastErrorExample.nothingUpdated)

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .delete("foo")
      } === false
    }
  }

  "update" should {
    "update citation if document is found" in {
      collection.update(any, any, any, any, any)(any, any, any) returns
        successful(LastErrorExample.oneExistingUpdated)

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .update("foo", SentenceUpdate("bar"))
      } === Some(SentenceUpdate("bar"))

      there was one(collection).update(
        selector = Matchers.eq(Json.obj("_id" -> "foo")),
        update = Matchers.eq(Json.obj("$set"->Json.obj("content" -> "bar"))),
        writeConcern = any,
        upsert = any,
        multi = any)(
          selectorWriter = any,
          updateWriter = any, ec = any)
    }

    "update citation if document is not found" in {
      collection.update(any, any, any, any, any)(any, any, any) returns
        successful(LastErrorExample.nothingUpdated)

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .update("foo", SentenceUpdate("bar"))
      } === None

      there was one(collection).update(
        selector = Matchers.eq(Json.obj("_id" -> "foo")),
        update = Matchers.eq(Json.obj("$set"->Json.obj("content" -> "bar"))),
        writeConcern = any,
        upsert = any,
        multi = any)(
          selectorWriter = any,
          updateWriter = any, ec = any)
    }
  }
}
