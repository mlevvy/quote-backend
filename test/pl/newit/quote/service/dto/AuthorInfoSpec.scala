package pl.newit.quote.service.dto

import org.specs2.mutable.Specification

import pl.newit.quote.author.dto.AuthorExample

class AuthorInfoSpec extends Specification {
  "valueOf" should {
    "create instance based on supplied argument" in {
      AuthorInfo.valueOf(AuthorExample.AlbertEinstein) === AuthorInfoExample.AlbertEinstein
    }
  }
}