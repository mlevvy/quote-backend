package pl.newit.common.play.api.mvc
import scala.util.control.Exception.catching

import org.joda.time.DateTime

import play.api.mvc.Result
import play.api.mvc.Results.BadRequest

trait JodaTime {
  def datetime(from: String)(block: DateTime => Result): Result =
    catching(classOf[IllegalArgumentException])
      .opt(new DateTime(from))
      .map(block)
      .getOrElse(BadRequest)
}