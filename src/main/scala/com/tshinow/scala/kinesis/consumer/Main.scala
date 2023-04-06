package com.tshinow.scala.kinesis.consumer

object Main extends Greeting with App {
  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "test！！"
}
