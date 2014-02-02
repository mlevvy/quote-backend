package pl.newit.quote.user.dto

import pl.newit.quote.common.Audit
import play.api.libs.functional.syntax._
import play.api.libs.json._

final case class User(
  id: String,
  email: String,
  firstName: String,
  lastName: String,
  audit: Audit)

object User {
  implicit val format: Format[User] = (
    (__ \ '_id).format[String] ~
    (__ \ 'email).format[String] ~
    (__ \ 'firstName).format[String] ~
    (__ \ 'lastName).format[String] ~
    (__ \ 'audit).format[Audit])(
      User.apply, unlift(User.unapply _))
}