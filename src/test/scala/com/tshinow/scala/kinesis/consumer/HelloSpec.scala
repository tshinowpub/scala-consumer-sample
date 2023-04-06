package com.tshinow.scala.kinesis.consumer

class HelloSpec extends munit.FunSuite {
  test("say hello") {
    assertEquals(Main.greeting, "hello")
  }
}
