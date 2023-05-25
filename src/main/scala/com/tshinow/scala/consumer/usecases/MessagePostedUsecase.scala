package com.tshinow.scala.consumer.usecases

import com.tshinow.scala.consumer.domain.channel.{ AccountId, ChannelId, MessageId }

final class MessagePostedUsecase {
  def run(messageId: MessageId, channelId: ChannelId, accountId: AccountId) = {
    println(s"Called MessagePostedUsecase. messageId: $messageId, channelId: $channelId, accountId: $accountId")
  }
}
