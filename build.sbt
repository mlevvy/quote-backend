name := "quotes"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "com.google.code.findbugs" % "jsr305" % "2.0.3",
  "com.google.guava" % "guava" % "17.0",
  "com.tzavellas" % "sse-guice" % "0.7.1",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.akka23-SNAPSHOT"
)     

scalaVersion := "2.11.1"
