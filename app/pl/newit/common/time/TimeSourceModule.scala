package pl.newit.common.time

import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

object TimeSourceModule extends ScalaModule {
  override def configure() {
    bind[TimeSource]
      .to[JodaTimeSource]
      .in[Singleton]
  }
}