name := "MyPurchases"

version := "2.6.x"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.11.12", "2.12.4")


resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  jdbc,
  "org.playframework.anorm" %% "anorm" % "2.6.2",
  "com.h2database" % "h2" % "1.4.196",
  "com.github.pureconfig" %% "pureconfig" % "0.9.1",
  "postgresql" % "postgresql" % "9.1-901.jdbc4"
//  "com.typesafe.play" %% "play-mailer" % "3.0.1"

)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies += guice
libraryDependencies += specs2 % Test
libraryDependencies += evolutions
libraryDependencies += "net.sf.barcode4j" % "barcode4j" % "2.0"
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.4.17"
//libraryDependencies += "com.typesafe.akka" % "akka-persistence_2.11" % "2.4.17"
//libraryDependencies += "net.kaliber" %% "play-mailer" % "6.0.0"

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.14"


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator






