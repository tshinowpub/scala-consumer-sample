package com.tshinow.scala.consumer.dbadapter.memory

import com.tshinow.scala.consumer.domain.channel.{ Message, MessageId, MessageRepository }

final class OnMemoryMessageRepository extends MessageRepository {

  var messages: Map[MessageId, Message] = Map.empty

  override def add(message: Message): Unit = {
    messages = messages + (message.getMessageId -> message)
  }

  override def findById(messageId: MessageId): Option[Message] = messages.get(messageId)
}
