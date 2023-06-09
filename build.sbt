import Dependencies._
import Settings.baseSettings
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.tshinow"
ThisBuild / organizationName := "tshinow"

lazy val root = (project in file("."))
  .settings(Settings.baseSettings)
  .settings(
    name := "scala-kinesis-consumer-v1-sample",
    libraryDependencies ++= Seq(
      munit % Test,
      scalatest,
      scalaMock,
      logback.classic,
      slf4j.api,
      slf4j.jclOverSlf4j,
      awsv1.kinesis,
      awsv2.sqs,
      akka.stream,
      zio.zio,
      zio.stream,
      circe.core,
      circe.parser,
      circe.generic,
      circe.genericExtras
    ),
    Compile / scalafmtOnCompile := true
  )
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

addCommandAlias("lint", ";scalafmtCheck;Test/scalafmtCheck;scalafmtSbtCheck;scalafixAll --check")
addCommandAlias("fmt", ";scalafmtAll;scalafmtSbt;scalafix RemoveUnused")
