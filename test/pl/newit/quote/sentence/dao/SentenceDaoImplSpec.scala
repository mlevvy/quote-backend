package pl.newit.quote.sentence.dao

import play.modules.reactivemongo.json.BSONFormats.PartialFormat
import reactivemongo.api.DB
import reactivemongo.bson.BSONDocument
import reactivemongo.core.commands.FindAndModify

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
import pl.newit.quote.sentence.dto.{SentenceInputExample, SentenceExample, Sentence, SentenceInput}
import pl.newit.test.concurrent._
import play.api.libs.json.{JsObject, Json}
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
      collection.db returns mock[DB]
      val format = implicitly[PartialFormat[BSONDocument]]

      collection.db.command[Option[BSONDocument]](any, any)(any) returns
        successful(Some(format.reads(SentenceExample.EqualityJson).get))

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .update("foo", SentenceInputExample.Equality)
      } === Some(SentenceExample.Equality)

      //TODO Better check this mock
      there was one(collection.db).command(any, any)(any)
    }

    "update citation if document is not found" in {
      collection.db returns mock[DB]
      val format = implicitly[PartialFormat[BSONDocument]]

      collection.db.command[Option[BSONDocument]](any, any)(any) returns
        successful(None)

      result {
        new SentenceDaoImpl(collection, clock, generator)
          .update("foo", SentenceInputExample.Equality)
      } === None

      //TODO Better check this mock
      there was one(collection.db).command(any, any)(any)
    }
  }
}
