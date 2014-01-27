package pl.newit.quote.common

import org.joda.time.DateTime

object Audits {
  val audit = Audit(
    creatorId = "creator",
    updaterId = "updater",
    create = new DateTime(),
    update = new DateTime())
}