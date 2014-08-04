package pl.newit.quote.sentence.dto

import org.specs2.mutable.Specification

import play.api.libs.json.JsSuccess

class SentenceInputSpec extends Specification {
  "reads" should {
    "read from JSON" in {
      SentenceInput.format.reads(SentenceInputExample.EqualityJson) ===
        JsSuccess(SentenceInputExample.Equality)
    }
  }
}