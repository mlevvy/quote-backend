package pl.newit.quote.common

import org.joda.time.DateTime

object AuditExample {
  val audit = Audit(
    creatorId = "creator",
    updaterId = "updater",
    create = new DateTime("2014-02-01T00:00:00.000Z"),
    update = new DateTime("2014-02-01T00:00:00.000Z"))
}