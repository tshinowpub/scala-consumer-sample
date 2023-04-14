import sbt._

object Version {

  val akkaStream = "2.6.20"
  val zioStream  = "2.0.10"

  val munit = "0.7.29"

  val scalaTest = "3.2.15"

  val logback = "1.4.6"
  val slf4j   = "2.0.5"

  val kinesis = "1.14.10"
  val awssdk  = "2.20.44"

  val mysqlConnectorJava = "8.0.32"
}

object Dependencies {

  val scalatest  = "org.scalatest" %% "scalatest" % Version.scalaTest
  lazy val munit = "org.scalameta" %% "munit"     % Version.munit

  object akka {
    val stream = "com.typesafe.akka" %% "akka-stream" % Version.akkaStream
  }

  object zio {
    val stream = "dev.zio" %% "zio-streams" % Version.zioStream
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

  object MySQLConnectorJava {
    val mysql = "com.mysql" % "mysql-connector-j" % Version.mysqlConnectorJava
  }
}
