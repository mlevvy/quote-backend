package controllers
import scala.concurrent.ExecutionContext.Implicits.global

import com.google.inject.Inject

import pl.newit.common.play.api.mvc.Collections
import pl.newit.common.play.api.mvc.JodaTime
import pl.newit.quote.service.SentenceService
import play.api.mvc.Action
import play.api.mvc.Controller

final class SentenceController @Inject() (service: SentenceService) extends Controller with JodaTime with Collections {
  def getAll(from: String) =
    Action.async(
      datetime(from)(
        service.getAll(_)
          .map(okOrNoContent(_))))
}