package pl.newit.quote.service.dto

import org.specs2.mutable.Specification

import pl.newit.quote.author.dto.Authors
import pl.newit.quote.sentence.dto.Sentences

class SentenceInfoSpec extends Specification {
  "valueOf" should {
    "create instance based on supplied arguments" in {
      SentenceInfo.valueOf(Sentences.Equality, Authors.AlbertEinstein) === SentenceInfos.Equality
    }
  }
}