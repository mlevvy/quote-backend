package pl.newit.quote.auth
import scala.concurrent.Future
import scala.concurrent.Future.successful

import play.api.mvc.ActionBuilder
import play.api.mvc.Request
import play.api.mvc.Results.Forbidden
import play.api.mvc.SimpleResult

object Authenticated extends ActionBuilder[AuthenticatedRequest] {
  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[SimpleResult]) =
    request.session.get("me").map(identity =>
      block(AuthenticatedRequest(identity, request)))
      .getOrElse(successful(Forbidden))
}