package com.tshinow.scala.consumer.domain.channel

case class MessageId(value: String) {
  require(value.nonEmpty)
}
