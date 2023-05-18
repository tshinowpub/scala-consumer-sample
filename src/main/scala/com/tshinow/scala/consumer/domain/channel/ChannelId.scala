package com.tshinow.scala.consumer.domain.channel

case class ChannelId(value: String) {
  require(value.nonEmpty)
}
