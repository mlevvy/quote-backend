package pl.newit.quote.author.dto

import pl.newit.quote.common.Audits

object Authors {
  val AlbertEinstein = Author(
    id = "a1",
    displayName = "Albert Einstein",
    audit = Audits.audit)

  val JerryLewis = Author(
    id = "a2",
    displayName = "Jerry Lewis",
    audit = Audits.audit)
}