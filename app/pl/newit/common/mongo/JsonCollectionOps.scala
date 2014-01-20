package pl.newit.common.mongo
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.libs.json.JsObject
import play.api.libs.json.Reads
import play.modules.reactivemongo.json.BSONFormats.PartialFormat
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.bson.BSONDocument
import reactivemongo.core.commands.FindAndModify
import reactivemongo.core.commands.Update

private[mongo] class JsonCollectionOps(value: JSONCollection) {
  private def findAndUpdate[T](query: JsObject, update: JsObject, returnUpdated: Boolean, fields: Option[JsObject],
    upsert: Boolean)(implicit f: PartialFormat[BSONDocument], r: Reads[T]): Future[Option[T]] =
    value.db.command(
      FindAndModify(
        collection = value.name,
        query = f.reads(query).get,
        modify = Update(f.reads(update).get, returnUpdated),
        fields = fields.map(f.reads(_).get),
        upsert = upsert))
      .map(_.map(f.writes(_))
        .map(_.as[T]))

  def findAndUpdate[T](query: JsObject, update: JsObject)(
    implicit f: PartialFormat[BSONDocument], r: Reads[T]): Future[Option[T]] =
    findAndUpdate(query, update, false, None, false)(f, r)

  def findAndUpdate[T](query: JsObject, update: JsObject, fields: Option[JsObject])(
    implicit f: PartialFormat[BSONDocument], r: Reads[T]): Future[Option[T]] =
    findAndUpdate(query, update, false, fields, false)(f, r)

  def findAndReturnUpdated[T](query: JsObject, update: JsObject)(
    implicit f: PartialFormat[BSONDocument], r: Reads[T]): Future[Option[T]] =
    findAndUpdate(query, update, true, None, false)(f, r)

  def findAndReturnUpdated[T](query: JsObject, update: JsObject, fields: Option[JsObject])(
    implicit f: PartialFormat[BSONDocument], r: Reads[T]): Future[Option[T]] =
    findAndUpdate(query, update, true, fields, false)(f, r)

  def upsert[T](query: JsObject, update: JsObject)(
    implicit f: PartialFormat[BSONDocument], r: Reads[T]): Future[Option[T]] =
    findAndUpdate(query, update, true, None, true)(f, r)
}