package pl.newit.common.mongo
import scala.language.implicitConversions

import org.joda.time.DateTime

import play.api.data.validation.ValidationError
import play.api.libs.json._
import play.modules.reactivemongo.json.collection.JSONCollection

package object json {
  implicit object DateTimeFormat extends Format[DateTime] {
    override def reads(json: JsValue) = json match {
      case JsObject(("$date", JsNumber(millis)) +: Nil) if millis.isValidLong =>
        JsSuccess(new DateTime(millis.toLong))
      case _ =>
        JsError(__ -> ValidationError("validate.error.expected.millis"))
    }
    override def writes(o: DateTime) =
      JsObject(("$date", JsNumber(o.getMillis)) +: Nil)
  }

  implicit def toJsonCollectionOps(collection: JSONCollection) =
    new JsonCollectionOps(collection)
}