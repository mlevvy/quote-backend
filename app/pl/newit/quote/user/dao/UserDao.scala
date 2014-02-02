package pl.newit.quote.user.dao
import scala.concurrent.Future

import pl.newit.quote.user.dto.User
import pl.newit.quote.user.dto.UserInput

trait UserDao {
  def getOrCreate(from: UserInput): Future[User]
}