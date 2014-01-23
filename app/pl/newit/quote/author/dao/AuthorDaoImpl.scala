package pl.newit.quote.author.dao
import scala.concurrent.ExecutionContext.Implicits.global

import com.google.inject.Inject
import com.google.inject.name.Named

import pl.newit.common.id.UniqueIdGenerator
import pl.newit.common.time.TimeSource
import pl.newit.quote.author.dto.Author
import pl.newit.quote.common.Audit
import play.api.libs.json.Json
import play.modules.reactivemongo.json.collection.JSONCollection

private[dao] class AuthorDaoImpl @Inject() (@Named("Author") collection: JSONCollection, clock: TimeSource,
  generator: UniqueIdGenerator) extends AuthorDao {

  def audit(creatorId: String): Audit =
    Audit.valueOf(creatorId, clock.now())

  def create(author: Author) =
    collection.insert(author)
      .map(_ => author)

  override def create(displayName: String, creatorId: String) =
    create(Author(
      id = generator.generate(),
      displayName = displayName,
      audit = audit(creatorId)))

  override def get(id: String) =
    collection.find(
      Json.obj("_id" -> id))
      .cursor[Author]
      .headOption

  override def getAll(ids: Iterable[String]) =
    collection.find(
      Json.obj("_id" ->
        Json.obj("$in" -> ids)))
      .cursor[Author]
      .enumerate()

  override def getAll() =
    collection.find(
      Json.obj())
      .cursor[Author]
      .enumerate()
}