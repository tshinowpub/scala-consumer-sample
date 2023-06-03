package com.tshinow.scala.consumer.domain.channel

abstract class Message(messageId: MessageId, channelId: ChannelId, accountId: AccountId, body: String) {

  def getMessageId: MessageId = messageId

  def getChannelId: ChannelId = channelId

  def getAccountId: AccountId = accountId

  def getBody: String = body
}

case class PostedMessage(
    messageId: MessageId,
    channelId: ChannelId,
    accountId: AccountId,
    body: String
) extends Message(messageId, channelId, accountId, body)
