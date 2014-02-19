package pl.newit.quote.user.dao
import com.google.inject.Inject
import com.google.inject.name.Named

import pl.newit.common.id.UniqueIdGenerator
import pl.newit.common.mongo.json._
import pl.newit.common.time.TimeSource
import pl.newit.quote.common.Audit
import pl.newit.quote.user.dto.User
import pl.newit.quote.user.dto.UserInput
import play.api.libs.json.Json.obj
import play.modules.reactivemongo.json.collection.JSONCollection

private[dao] class UserDaoImpl @Inject() (@Named("User") collection: JSONCollection, clock: TimeSource,
  generator: UniqueIdGenerator) extends UserDao {

  def audit(creatorId: String): Audit =
    Audit.valueOf(creatorId, clock.now())

  def user(id: String, from: UserInput): User =
    User(
      id = id,
      email = from.email,
      firstName = from.firstName,
      lastName = from.lastName,
      audit = audit(id))

  def user(from: UserInput): User =
    user(generator.generate(), from)

  override def getOrCreate(from: UserInput) =
    collection.upsert[User](
      query = obj("email" -> from.email),
      update = obj("$setOnInsert" -> user(from)))
}