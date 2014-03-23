name := "quotes"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  cache,
  "org.specs2" %% "specs2" % "2.3.7" % "test",
  "com.google.code.findbugs" % "jsr305" % "2.0.3",
  "com.google.guava" % "guava" % "15.0",
  "com.tzavellas" % "sse-guice" % "0.7.1",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.2"
)     

play.Project.playScalaSettings

scalaVersion := "2.10.3"
