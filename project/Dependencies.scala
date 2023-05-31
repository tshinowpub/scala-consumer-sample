import sbt._

object Version {

  val akkaStream = "2.6.20"
  val zio        = "2.0.10"

  val munit = "0.7.29"

  val scalaTest = "3.2.16"
  val scalaMock = "5.2.0"

  val logback = "1.4.6"
  val slf4j   = "2.0.5"

  val kinesis = "1.14.10"
  val awssdk  = "2.20.44"

  val circe              = "0.14.5"
  val circeGenericExtras = "0.14.3"

  val mysqlConnectorJava = "8.0.32"
}

object Dependencies {

  val scalatest  = "org.scalatest" %% "scalatest" % Version.scalaTest
  val scalaMock  = "org.scalamock" %% "scalamock" % Version.scalaMock
  lazy val munit = "org.scalameta" %% "munit"     % Version.munit

  object akka {
    val stream = "com.typesafe.akka" %% "akka-stream" % Version.akkaStream
  }

  object zio {

    val zio    = "dev.zio" %% "zio"         % Version.zio
    val stream = "dev.zio" %% "zio-streams" % Version.zio
  }

  object logback {
    val classic = "ch.qos.logback" % "logback-classic" % Version.logback excludeAll (
      ExclusionRule("org.slf4j")
    )
  }

  object slf4j {

    val api          = "org.slf4j" % "slf4j-api"      % Version.slf4j
    val jclOverSlf4j = "org.slf4j" % "jcl-over-slf4j" % Version.slf4j
  }

  object awsv1 {
    val kinesis = "com.amazonaws" % "amazon-kinesis-client" % Version.kinesis
  }
  object awsv2 {
    val sqs = "software.amazon.awssdk" % "sqs" % Version.awssdk
  }

  object circe {

    val core          = "io.circe" %% "circe-core"           % Version.circe
    val parser        = "io.circe" %% "circe-parser"         % Version.circe
    val generic       = "io.circe" %% "circe-generic"        % Version.circe
    val genericExtras = "io.circe" %% "circe-generic-extras" % Version.circeGenericExtras
  }

  object MySQLConnectorJava {
    val mysql = "com.mysql" % "mysql-connector-j" % Version.mysqlConnectorJava
  }
}
