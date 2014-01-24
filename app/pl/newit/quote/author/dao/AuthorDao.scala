package pl.newit.quote.author.dao
import scala.concurrent.Future

import pl.newit.quote.author.dto.Author
import play.api.libs.iteratee.Enumerator

trait AuthorDao {
  def create(displayName: String, creatorId: String): Future[Author]

  def get(id: String): Future[Option[Author]]

  def getAll(ids: Iterable[String]): Enumerator[Author]

  def getAll(): Enumerator[Author]
}