package pl.newit.common.id.uuid
import com.google.inject.Singleton
import com.tzavellas.sse.guice.ScalaModule

import pl.newit.common.id.UniqueIdGenerator

object UuidGeneratorModule extends ScalaModule {
  override def configure() {
    bind[UniqueIdGenerator]
      .to[UuidGenerator]
      .in[Singleton]
  }
}