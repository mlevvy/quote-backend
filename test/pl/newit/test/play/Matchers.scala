package pl.newit.test.play
import scala.concurrent.Future.successful

import org.specs2.matcher.AnyMatchers

import play.api.mvc.SimpleResult
import play.api.test.Helpers._

object Matchers extends AnyMatchers {

  def haveStatus(s: => Int) =
    ===(s) ^^ ((t: SimpleResult) => status(successful(t)))

  def haveHeadersEqualTo(h: => Map[String, String]) =
    ===(h) ^^ ((t: SimpleResult) => headers(successful(t)))

  def haveContentEqualTo(c: => Array[Byte]) =
    ===(c) ^^ ((t: SimpleResult) => contentAsBytes(successful(t)))

  def beEqualToResult(x: => SimpleResult) = {
    val b = successful(x) // value must be evaluated once
    haveStatus(status(b)) and haveHeadersEqualTo(headers(b)) and haveContentEqualTo(contentAsBytes(b))
  }

}