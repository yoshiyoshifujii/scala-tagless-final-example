name := "scala-tagless-final-example"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.typelevel" %% "cats-core" % "1.5.0",
  "org.typelevel" %% "cats-free" % "1.5.0"
)
