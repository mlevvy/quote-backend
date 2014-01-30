package pl.newit.quote.sentence.dao

import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.common.id.uuid.UuidGeneratorModule
import pl.newit.common.time.TimeSourceModule
import pl.newit.quote.mongo.MongoModule

object SentenceDaoModule extends ScalaModule {
  override def configure() {
    install(MongoModule)
    install(TimeSourceModule)
    install(UuidGeneratorModule)

    bind[SentenceDao]
      .to[SentenceDaoImpl]
      .in[Singleton]
  }
}