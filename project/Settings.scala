import sbt._
import sbt.Keys.{ scalaVersion, _ }
import Dependencies._
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._
import scalafix.sbt.ScalafixPlugin.autoImport.{ scalafixScalaBinaryVersion, scalafixSemanticdb }

object Settings {
  val baseSettings = Seq(
    organization                           := "com.tshinow",
    version                                := "1.0.0-SNAPSHOT",
    scalaVersion                           := "2.13.10",
    ThisBuild / scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value),
    semanticdbEnabled                      := true,
    semanticdbVersion                      := scalafixSemanticdb.revision,
    scalacOptions ++=
      Seq(
        "-feature",
        "-deprecation",
        "-unchecked",
        "-encoding",
        "UTF-8",
        "-language:_",
        "-release:17",
        "-Yrangepos",
        "-Ywarn-unused",
        "-Xfatal-warnings"
      ),
    libraryDependencies ++= Seq(
      scalatest % Test
    ),
    Compile / scalafmtOnCompile := true
  )
}
