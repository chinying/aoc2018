import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.chinying",
      scalaVersion := "2.12.7",
      version      := "aoc18-0.1.0-SNAPSHOT"
    )),
    name := "aoc18",
    libraryDependencies += scalaTest % Test
  )
