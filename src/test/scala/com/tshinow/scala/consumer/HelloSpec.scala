package com.tshinow.scala.consumer

class HelloSpec extends munit.FunSuite {
  test("say hello") {
    assertEquals(Main.greeting, "hello")
  }
}
