package controllers
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.successful

import com.google.inject.Inject

import pl.newit.common.play.api.mvc.Collections
import pl.newit.common.play.api.mvc.JodaTime
import pl.newit.quote.auth.Authenticated
import pl.newit.quote.service.SentenceService
import pl.newit.quote.service.dto.SentenceInput
import play.api.mvc.Controller

final class SentenceController @Inject() (service: SentenceService) extends Controller with JodaTime with Collections {
  def create(authorId: String) =
    Authenticated.async(parse.json)(request =>
      request.body.validate[SentenceInput]
        .map(input => successful(NotImplemented))
        .recoverTotal(_ => successful(BadRequest)))

  def getAll(from: String) =
    Authenticated.async(
      datetime(from)(
        service.getAll(_)
          .map(okOrNoContent(_))))
}