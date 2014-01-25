package pl.newit.common.play.api.mvc
import scala.concurrent.Future
import scala.concurrent.Future.successful
import scala.util.control.Exception.catching

import org.joda.time.DateTime

import play.api.mvc.Results.BadRequest
import play.api.mvc.SimpleResult

trait JodaTime {
  def datetime(from: String)(block: DateTime => Future[SimpleResult]): Future[SimpleResult] =
    catching(classOf[IllegalArgumentException])
      .opt(new DateTime(from))
      .map(block)
      .getOrElse(successful(BadRequest))
}