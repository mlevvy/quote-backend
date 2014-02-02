package pl.newit.quote.user.dao

import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.common.id.uuid.UuidGeneratorModule
import pl.newit.common.time.TimeSourceModule
import pl.newit.quote.mongo.MongoModule

object UserDaoModule extends ScalaModule {
  override def configure() {
    install(MongoModule)
    install(TimeSourceModule)
    install(UuidGeneratorModule)

    bind[UserDao]
      .to[UserDaoImpl]
      .in[Singleton]
  }
}