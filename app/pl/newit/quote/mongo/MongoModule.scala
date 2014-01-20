package pl.newit.quote.mongo
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.name.Named
import com.tzavellas.sse.guice.ScalaModule

import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.DefaultDB

object MongoModule extends ScalaModule {
  def configure() {
    bind[DefaultDB]
      .toProvider[DatabaseProvider]
      .in[Singleton]
  }

  @Provides
  @Singleton
  @Named("Author")
  def author(db: DefaultDB): JSONCollection =
    db.collection("author")

  @Provides
  @Singleton
  @Named("Sentence")
  def sentence(db: DefaultDB): JSONCollection =
    db.collection("sentence")
}