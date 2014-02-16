package pl.newit.quote.service.dto

import org.specs2.mutable.Specification

import pl.newit.quote.author.dto.AuthorExample
import pl.newit.quote.sentence.dto.SentenceExample

class SentenceInfoSpec extends Specification {
  "valueOf" should {
    "create instance based on supplied arguments" in {
      SentenceInfo.valueOf(SentenceExample.Equality, AuthorExample.AlbertEinstein) === SentenceInfoExample.Equality
    }
  }
}