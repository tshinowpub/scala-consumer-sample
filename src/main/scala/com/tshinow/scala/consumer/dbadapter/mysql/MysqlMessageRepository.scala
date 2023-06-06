package com.tshinow.scala.consumer.dbadapter.mysql

import com.tshinow.scala.consumer.domain.channel.{ Message, MessageId, MessageRepository }

class MysqlMessageRepository extends MessageRepository {

  override def add(message: Message): Unit = {
    message.getChannelId

    message.getAccountId

    message.getBody
  }

  override def findById(messageId: MessageId): Option[Message] = {
    None
  }
}
