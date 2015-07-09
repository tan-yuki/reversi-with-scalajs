logLevel := Level.Warn


resolvers += Resolver.url("GitHub repository", url("http://shaggyyeti.github.io/releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.4")
addSbtPlugin("default" % "sbt-sass" % "0.1.9")
