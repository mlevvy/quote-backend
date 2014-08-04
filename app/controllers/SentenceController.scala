package controllers
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.successful

import com.google.inject.Inject

import pl.newit.common.play.api.mvc.Collections
import pl.newit.common.play.api.mvc.JodaTime
import pl.newit.quote.sentence.dto.SentenceInput
import pl.newit.quote.service.SentenceService
import pl.newit.quote.service.dto.SentenceUpdate
import play.api.libs.json.JsValue
import play.api.libs.json.Json.toJson
import play.api.mvc.Action
import play.api.mvc.Controller

final class SentenceController @Inject() (service: SentenceService) extends Controller with JodaTime with Collections {

  def create(from: SentenceInput) =
    service.create(from).map {
      case Some(ok) => Created(toJson(ok))
      case None => UnprocessableEntity
    }

  def create(): Action[JsValue] =
    Action.async(parse.json)(request =>
      request.body.validate[SentenceInput]
        .map(create)
        .recoverTotal(_ => successful(BadRequest)))

  def update(from: SentenceUpdate, sentenceId: String) =
    service.update(from, sentenceId).map {
      case Some(ok) => Ok(toJson(ok))
      case None => NotFound
    }

  def update(sentenceId: String): Action[JsValue] =
    Action.async(parse.json)(request =>
      request.body.validate[SentenceUpdate]
        .map(update(_, sentenceId))
        .recoverTotal(_ => successful(BadRequest)))

  def getAll(from: String) =
    Action.async(
      datetime(from)(
        service.getAll(_)
          .map(okOrNoContent(_))))
}