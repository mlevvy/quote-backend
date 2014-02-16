package controllers
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.successful

import com.google.inject.Inject

import pl.newit.common.play.api.mvc.Collections
import pl.newit.common.play.api.mvc.JodaTime
import pl.newit.quote.auth.Authenticated
import pl.newit.quote.service.SentenceService
import pl.newit.quote.service.dto.SentencePartialInput
import play.api.libs.json.JsValue
import play.api.libs.json.Json.toJson
import play.api.mvc.Action
import play.api.mvc.Controller

final class SentenceController @Inject() (service: SentenceService) extends Controller with JodaTime with Collections {

  def create(from: SentencePartialInput, authorId: String) =
    service.create(from, authorId).map {
      case Some(ok) => Created(toJson(ok))
      case None => NotFound
    }

  def create(authorId: String): Action[JsValue] =
    Action.async(parse.json)(request =>
      request.body.validate[SentencePartialInput]
        .map(create(_, authorId))
        .recoverTotal(_ => successful(BadRequest)))

  def getAll(from: String) =
    Action.async(
      datetime(from)(
        service.getAll(_)
          .map(okOrNoContent(_))))
}