package pl.newit.quote.author.dao

import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.common.id.uuid.UuidGeneratorModule
import pl.newit.common.time.TimeSourceModule
import pl.newit.quote.mongo.MongoModule

object AuthorDaoModule extends ScalaModule {
  override def configure() {
    install(MongoModule)
    install(TimeSourceModule)
    install(UuidGeneratorModule)

    bind[AuthorDao]
      .to[AuthorDaoImpl]
      .in[Singleton]
  }
}