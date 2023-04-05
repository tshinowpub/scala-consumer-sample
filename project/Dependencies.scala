import sbt._

object Version {

  val scalaTest = "3.2.15"

  val logback = "1.4.6"
  val slf4j   = "2.0.5"
}

object Dependencies {

  val scalatest  = "org.scalatest" %% "scalatest" % Version.scalaTest
  lazy val munit = "org.scalameta" %% "munit"     % "0.7.29"

  object logback {
    val classic = "ch.qos.logback" % "logback-classic" % Version.logback excludeAll (
      ExclusionRule("org.slf4j")
    )
  }

  object slf4j {

    val api          = "org.slf4j" % "slf4j-api"      % Version.slf4j
    val jclOverSlf4j = "org.slf4j" % "jcl-over-slf4j" % Version.slf4j
  }
}
