package com.tshinow.scala.consumer.domain.channel
case class AccountId(value: String) {
  require(value.nonEmpty)
}
