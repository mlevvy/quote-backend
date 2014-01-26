package pl.newit.quote.service.dto

object SentenceInfos {
  val sentence1 = SentenceInfo(
    id = "s1",
    content = "Lorem ipsum",
    author = AuthorInfos.author2)

  val sentence2 = SentenceInfo(
    id = "s2",
    content = "dolor sit amet",
    author = AuthorInfos.author2)

  val sentence3 =
    SentenceInfo(
      id = "s3",
      content = "consectetur adipisicing elit",
      author = AuthorInfos.author2)
}