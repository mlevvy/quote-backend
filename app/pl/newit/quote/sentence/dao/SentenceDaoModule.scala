package pl.newit.quote.sentence.dao

import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.quote.mongo.MongoModule

object SentenceDaoModule extends ScalaModule {
  override def configure() {
    install(MongoModule)

    bind[SentenceDao]
      .to[SentenceDaoImpl]
      .in[Singleton]
  }
}