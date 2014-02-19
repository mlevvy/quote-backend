package pl.newit.quote.author.dto

import pl.newit.quote.common.AuditExample

object AuthorExample {
  val AlbertEinstein = Author(
    id = "a1",
    displayName = "Albert Einstein",
    audit = AuditExample.audit)

  val JerryLewis = Author(
    id = "a2",
    displayName = "Jerry Lewis",
    audit = AuditExample.audit)
}