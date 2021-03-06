package pl.newit.common.play.api.mvc
import scala.concurrent.Future.successful

import org.joda.time.DateTime
import org.specs2.mutable.Specification

import pl.newit.test.concurrent._
import pl.newit.test.play.Matchers._
import play.api.mvc.Results.BadRequest
import play.api.mvc.Results.InternalServerError
import play.api.mvc.Results.Ok

class JodaTimeSpec extends Specification with JodaTime {
  "datetime" should {
    "convert argument to DateTime and execute block" in {
      result {
        datetime("2014-01-24T22:15:50.981+01:00") { dt =>
          dt === new DateTime("2014-01-24T22:15:50.981+01:00")
          successful(Ok)
        }
      } must beEqualToResult(Ok)
    }

    "return BadRequest in case of illegal argument" in {
      result {
        datetime("foo")(_ => successful(InternalServerError))
      } must beEqualToResult(BadRequest)
    }

    "do not catch exceptions inside block" in {
      datetime("2014-01-24T22:15:50.981+01:00") { dt =>
        throw new IllegalArgumentException(dt.toString())
      } must throwA(new IllegalArgumentException("2014-01-24T22:15:50.981+01:00"))
    }
  }
}