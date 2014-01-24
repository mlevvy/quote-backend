package pl.newit.common.play.api.mvc
import org.joda.time.DateTime
import org.specs2.mutable.Specification

import play.api.mvc.Results.BadRequest
import play.api.mvc.Results.InternalServerError
import play.api.mvc.Results.Ok

class JodaTimeSpec extends Specification with JodaTime {
  "datetime" should {
    "convert argument to DateTime and execute block" in {
      datetime("2014-01-24T22:15:50.981+01:00") { dt =>
        dt === new DateTime("2014-01-24T22:15:50.981+01:00")
        Ok
      } === Ok
    }

    "return BadRequest in case of illegal argument" in {
      datetime("foo")(_ => InternalServerError) === BadRequest
    }

    "do not catch exceptions inside block" in {
      datetime("2014-01-24T22:15:50.981+01:00") { dt =>
        throw new IllegalArgumentException(dt.toString())
      } must throwA(new IllegalArgumentException("2014-01-24T22:15:50.981+01:00"))
    }
  }
}