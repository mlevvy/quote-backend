package pl.newit.quote.service.dto

import org.specs2.mutable.Specification

import play.api.libs.json.JsSuccess

class SentencePartialInputSpec extends Specification {
  "reads" should {
    "read from JSON" in {
      SentencePartialInput.reads.reads(SentencePartialInputExample.EqualityJson) ===
        JsSuccess(SentencePartialInputExample.Equality)
    }
  }
}