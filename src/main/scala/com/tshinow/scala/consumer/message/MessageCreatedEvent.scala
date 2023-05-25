package com.tshinow.scala.consumer.message

import com.tshinow.scala.consumer.domain.channel.{ AccountId, ChannelId, MessageId }

sealed trait MessageCreatedEvent extends Product with Serializable

object MessageCreatedEvent {

  final case class MessagePosted(
      messageId: MessageId,
      channelId: ChannelId,
      accountId: AccountId,
      body: String
  ) extends MessageCreatedEvent

  final case class UserAdded(
      messageId: MessageId,
      channelId: ChannelId,
      accountId: AccountId,
      addedAccountIds: Vector[AccountId],
      body: String
  ) extends MessageCreatedEvent

  final case class JoinBy(
      messageId: MessageId,
      channelId: ChannelId,
      accountId: AccountId,
      body: String
  ) extends MessageCreatedEvent

  final case class UnSupportedEvent(body: String) extends MessageCreatedEvent
}
