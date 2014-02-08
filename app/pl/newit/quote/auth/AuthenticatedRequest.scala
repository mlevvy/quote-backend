package pl.newit.quote.auth

import play.api.mvc.Request
import play.api.mvc.WrappedRequest

final case class AuthenticatedRequest[A](
  identity: String,
  request: Request[A]) extends WrappedRequest[A](request)