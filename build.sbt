name := "first-sample"

version := "2.6.x"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.11.12", "2.12.4")



libraryDependencies ++= Seq(
  jdbc,
  "org.playframework.anorm" %% "anorm" % "2.6.2",
  "com.h2database" % "h2" % "1.4.196",
  "com.github.pureconfig" %% "pureconfig" % "0.9.1",
  "postgresql" % "postgresql" % "9.1-901.jdbc4"

  //  "com.typesafe.play" %% "play-mailer" % "3.0.1"

)

//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Sonatype OSS Releases"  at "https://oss.sonatype.org/content/repositories/releases"


libraryDependencies += guice
libraryDependencies += specs2 % Test
libraryDependencies += evolutions
libraryDependencies += ws
libraryDependencies += ehcache

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.14"
libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"

libraryDependencies += "net.sf.barcode4j" % "barcode4j" % "2.0"
libraryDependencies += "org.jsoup" % "jsoup" % "1.11.3"



// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator






