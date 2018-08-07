// Comment to get more information during initialization
logLevel := Level.Warn

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.13")

resolvers := Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)
