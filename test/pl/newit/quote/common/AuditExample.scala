package pl.newit.quote.common

import org.joda.time.DateTime

object AuditExample {
  val audit = Audit(
    creatorId = "creator",
    updaterId = "updater",
    create = new DateTime(),
    update = new DateTime())
}