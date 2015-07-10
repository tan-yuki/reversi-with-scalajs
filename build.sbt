enablePlugins(ScalaJSPlugin)

name := "reversi"

version := "1.0"

scalaVersion := "2.11.7"

jsDependencies += RuntimeDOM

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.1"
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")

skip in packageJSDependencies := false

persistLauncher in Compile := true
persistLauncher in Test := false
