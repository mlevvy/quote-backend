package pl.newit.quote.service

import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.quote.author.dao.AuthorDaoModule
import pl.newit.quote.sentence.dao.SentenceDaoModule

object SentenceServiceModule extends ScalaModule {
  override def configure() {
    install(AuthorDaoModule)
    install(SentenceDaoModule)

    bind[SentenceService]
      .to[SentenceServiceImpl]
      .in[Singleton]
  }
}