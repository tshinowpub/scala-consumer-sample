package com.tshinow.scala.consumer.domain.channel

trait MessageRepository {

  def add(message: Message): Unit

  def findById(messageId: MessageId): Option[Message]
}
