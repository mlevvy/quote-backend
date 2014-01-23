package pl.newit.quote.common

import org.joda.time.DateTime

import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json._

final case class Audit(
  creatorId: String,
  updaterId: String,
  create: DateTime,
  update: DateTime)

object Audit {
  implicit val format = Json.format[Audit]

  def valueOf(creatorId: String, now: DateTime): Audit =
    apply(creatorId, creatorId, now, now)
}