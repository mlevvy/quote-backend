package pl.newit.common.play.api.mvc
import play.api.libs.json.Json.toJson

import play.api.libs.json.Writes
import play.api.mvc.Results.NoContent
import play.api.mvc.Results.Ok

trait Collections {
  def okOrNoContent[T](tr: Traversable[T])(implicit w: Writes[T]) =
    if (!tr.isEmpty) Ok(toJson(tr)) else NoContent
}