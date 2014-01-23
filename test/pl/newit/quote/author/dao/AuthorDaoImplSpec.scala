package pl.newit.quote.author.dao
import scala.concurrent.Future.successful

import org.joda.time.DateTime
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import pl.newit.common.id.UniqueIdGenerator
import pl.newit.common.time.TimeSource
import pl.newit.quote.author.dto.Author
import pl.newit.quote.common.Audit
import pl.newit.test.concurrent._
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.core.commands.LastError

class AuthorDaoImplSpec extends Specification with Mockito {
  "create" should {
    "insert new document in collection" in {
      val clock = mock[TimeSource]
      val generator = mock[UniqueIdGenerator]
      val collection = mock[JSONCollection]

      val lastError = LastError(
        ok = true,
        err = None,
        code = None,
        errMsg = None,
        originalDocument = None,
        updated = 1,
        updatedExisting = false)

      collection.insert[Author](document = any, writeConcern = any)(
        writer = any, ec = any) returns successful(lastError)

      clock.now() returns new DateTime("2004-02-12T15:19:21Z")
      generator.generate() returns "foo"

      val expected = Author(
        id = "foo", displayName = "Albert Einstein",
        audit = Audit(
          creatorId = "user",
          updaterId = "user",
          create = new DateTime("2004-02-12T15:19:21Z"),
          update = new DateTime("2004-02-12T15:19:21Z")))

      result {
        new AuthorDaoImpl(collection, clock, generator)
          .create("Albert Einstein", "user")
      } === expected
    }
  }
}