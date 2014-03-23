package controllers
import scala.concurrent.Future.successful

import org.mockito.Mockito.reset
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeExample

import pl.newit.quote.service.SentenceService
import pl.newit.quote.service.dto.SentenceInfoExample
import pl.newit.quote.service.dto.SentencePartialInputExample
import pl.newit.test.concurrent._
import pl.newit.test.play.Matchers._
import play.api.libs.json.Json
import play.api.libs.json.JsObject
import play.api.mvc.Results._
import play.api.test._
import play.api.test.Helpers._

class SentenceControllerSpec extends Specification with Mockito with BeforeExample {

  val service = mock[SentenceService]
  override def before() = reset(service)

  def newController = new SentenceController(service)

  "create" should {
    def request(body: JsObject) =
      newController.create("einstein")(
        FakeRequest(
          method = POST,
          uri = routes.SentenceController.create("einstein").url,
          headers = FakeHeaders(),
          body = body))

    "return newly Created sentence" in {
      service.create(any, any) returns successful(Some(SentenceInfoExample.Equality))

      result {
        request(body = SentencePartialInputExample.EqualityJson)
      } must beEqualToResult(Created(Json.toJson(SentenceInfoExample.Equality)))

      there was {
        one(service).create(SentencePartialInputExample.Equality, "einstein")
        noMoreCallsTo(service)
      }
    }

    "return NotFound if there is no author" in {
      service.create(any, any) returns successful(None)

      result {
        request(body = SentencePartialInputExample.EqualityJson)
      } must beEqualToResult(NotFound)
    }

    "return BadRequest for illegal JSON" in {
      result {
        request(body = SentencePartialInputExample.EqualityJson - SentencePartialInputExample.EqualityJson.keys.head)
      } must beEqualToResult(BadRequest)
    }
  }

  "getAll" should {
    "return Ok if there are sentences" in {
      service.getAll(any) returns successful(List(SentenceInfoExample.Equality, SentenceInfoExample.Imagination))

      result {
        newController.getAll("2012-01-01T00:00:00.000+01:00")(
          FakeRequest(GET, "/sentence?since=2012-01-01T00:00:00.000+01:00"))
      } must beEqualToResult(Ok(Json.arr(SentenceInfoExample.Equality, SentenceInfoExample.Imagination)))
    }

    "return NoContent if there is no sentences" in {
      service.getAll(any) returns successful(Nil)

      result {
        newController.getAll("2012-01-01T00:00:00.000+01:00")(
          FakeRequest(GET, "/sentence?since=2012-01-01T00:00:00.000+01:00"))
      } must beEqualToResult(NoContent)
    }
  }
}