package pl.newit.quote.service.dto
import org.joda.time.DateTime

import pl.newit.quote.author.dto.Author
import pl.newit.quote.sentence.dto.Sentence
import play.api.libs.json._

final case class SentenceInfo(
  id: String,
  forDay: DateTime,
  content: String,
  author: AuthorInfo)

object SentenceInfo {
  implicit val writes = Json.writes[SentenceInfo]

  def valueOf(sentence: Sentence, author: Author) =
    apply(id = sentence.id,
      forDay = sentence.forDay,
      content = sentence.content,
      author = AuthorInfo.valueOf(author))
}