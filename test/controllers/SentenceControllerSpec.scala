package controllers
import scala.concurrent.Future.successful

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import pl.newit.quote.service.SentenceService
import pl.newit.quote.service.dto.SentenceInfos
import pl.newit.test.concurrent._
import pl.newit.test.play.Matchers._
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.test._
import play.api.test.Helpers._

class SentenceControllerSpec extends Specification with Mockito {
  "getAll" should {
    "return Ok if there are sentences" in {
      val service = mock[SentenceService]

      service.getAll(any) returns successful(List(SentenceInfos.sentence1, SentenceInfos.sentence2))

      result {
        new SentenceController(service).getAll("2012-01-01T00:00:00.000+01:00")(
          FakeRequest(GET, "/sentence?since=2012-01-01T00:00:00.000+01:00"))
      } must beEqualToResult(Ok(Json.arr(SentenceInfos.sentence1, SentenceInfos.sentence2)))
    }

    "return NoContent if there is no sentences" in {
      val service = mock[SentenceService]

      service.getAll(any) returns successful(Nil)

      result {
        new SentenceController(service).getAll("2012-01-01T00:00:00.000+01:00")(
          FakeRequest(GET, "/sentence?since=2012-01-01T00:00:00.000+01:00"))
      } must beEqualToResult(NoContent)
    }
  }
}