package controllers
import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.quote.service.SentenceServiceModule

object ControllerModule extends ScalaModule {
  override def configure() {
    install(SentenceServiceModule)

    bind[SentenceController]
      .in[Singleton]
  }
}