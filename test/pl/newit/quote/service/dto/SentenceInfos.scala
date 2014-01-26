package pl.newit.quote.service.dto

object SentenceInfos {
  val Equality = SentenceInfo(
    id = "s1",
    content = "Before God we are all equally wise - and equally foolish.",
    author = AuthorInfos.AlbertEinstein)

  val Imagination = SentenceInfo(
    id = "s2",
    content = "Imagination is more important than knowledge...",
    author = AuthorInfos.AlbertEinstein)

  val Woman =
    SentenceInfo(
      id = "s3",
      content = "Every man's dream is to be able to sink into the arms of a woman without also falling into her hands.",
      author = AuthorInfos.JerryLewis)
}