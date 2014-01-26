package pl.newit.quote.author.dto

import pl.newit.quote.common.Audits

object Authors {
  val author1 = Author(
    id = "a1",
    displayName = "Baz Faz",
    audit = Audits.audit)

  val author2 = Author(
    id = "a2",
    displayName = "Foo Bar",
    audit = Audits.audit)
}