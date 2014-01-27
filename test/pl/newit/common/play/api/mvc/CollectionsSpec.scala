package pl.newit.common.play.api.mvc

import org.specs2.mutable.Specification

import pl.newit.test.play.Matchers._
import play.api.libs.json.Json
import play.api.mvc.Results.NoContent
import play.api.mvc.Results.Ok

class CollectionsSpec extends Specification with Collections {
  "okOrNoContent" should {
    "return Ok for non-empty argument" in {
      okOrNoContent(List("foo", "bar")) must beEqualToResult(Ok(Json.arr("foo", "bar")))
    }

    "return NoContent for empty argument" in {
      okOrNoContent(List.empty[String]) must beEqualToResult(NoContent)
      okOrNoContent(Traversable.empty[String]) must beEqualToResult(NoContent)
    }
  }
}