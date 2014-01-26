package pl.newit.quote.service.dto
import pl.newit.quote.author.dto.Author

import play.api.libs.json._

final case class AuthorInfo(
  id: String,
  displayName: String)

object AuthorInfo {
  implicit val writes = Json.writes[AuthorInfo]

  def valueOf(author: Author) =
    apply(author.id, author.displayName)
}