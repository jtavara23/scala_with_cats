name := "cats-sandbox"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.1"

scalafmtOnCompile := true

libraryDependencies ++=
  Seq(
    "org.typelevel" %% "cats-core" % "2.0.0",
      "org.scalaz" %% "scalaz-core" % "7.2.30",
    "org.scalaz" %% "scalaz-concurrent" % "7.2.30")


// scalac options come from the sbt-tpolecat plugin so need to set any here

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

