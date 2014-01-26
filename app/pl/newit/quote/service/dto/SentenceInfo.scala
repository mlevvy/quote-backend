package pl.newit.quote.service.dto
import pl.newit.quote.author.dto.Author
import pl.newit.quote.sentence.dto.Sentence

import play.api.libs.json._

final case class SentenceInfo(
  id: String,
  content: String,
  author: AuthorInfo)

object SentenceInfo {
  implicit val writes = Json.writes[SentenceInfo]

  def valueOf(sentence: Sentence, author: Author) =
    apply(sentence.id, sentence.content, AuthorInfo.valueOf(author))
}