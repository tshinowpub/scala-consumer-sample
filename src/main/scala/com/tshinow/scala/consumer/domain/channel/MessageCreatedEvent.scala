package com.tshinow.scala.consumer.domain.channel

sealed trait MessageCreatedEvent extends Product with Serializable

object MessageCreatedEvent {

  final case class MessagePosted(
      messageId: MessageId,
      channelId: ChannelId,
      postedBy: AccountId,
      body: String
  ) extends MessageCreatedEvent

  final case class Reply(
      messageId: MessageId,
      channelId: ChannelId,
      postedBy: AccountId,
      replyTo: MessageId,
      body: String
  ) extends MessageCreatedEvent

  final case class Mention(
      messageId: MessageId,
      channelId: ChannelId,
      postedBy: AccountId,
      replyTo: AccountId,
      body: String
  ) extends MessageCreatedEvent

  final case class ReplyWithMention(
      messageId: MessageId,
      channelId: ChannelId,
      postedBy: AccountId,
      replyTo: MessageId,
      mentionTo: Vector[AccountId],
      body: String
  ) extends MessageCreatedEvent

  final case class UserAdded(
      messageId: MessageId,
      channelId: ChannelId,
      postedBy: AccountId,
      addedAccountIds: Vector[AccountId],
      body: String
  ) extends MessageCreatedEvent

  final case class JoinToChannel(
      messageId: MessageId,
      channelId: ChannelId,
      joinedAccountId: AccountId,
      body: String
  ) extends MessageCreatedEvent

  final case class UnSupportedEvent(body: String) extends MessageCreatedEvent
}
