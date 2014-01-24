package pl.newit.quote.mongo

import com.google.inject.Provider

import play.api.Play.current
import play.modules.reactivemongo.ReactiveMongoPlugin
import reactivemongo.api.DefaultDB

private[mongo] class DatabaseProvider extends Provider[DefaultDB] {
  override def get() =
    ReactiveMongoPlugin.db
}
