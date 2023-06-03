package com.tshinow.scala.consumer.usecases

import com.tshinow.scala.consumer.domain.channel.{ AccountId, ChannelId, MessageId, MessageRepository, PostedMessage }

import scala.concurrent.Future

final class MessagePostedUsecase(repository: MessageRepository) {
  def run(messageId: MessageId, channelId: ChannelId, accountId: AccountId, body: String): Future[Unit] = {
    println(s"Called MessagePostedUsecase. messageId: $messageId, channelId: $channelId, accountId: $accountId")

    repository.add(PostedMessage(messageId, channelId, accountId, body))

    Future.successful(())
  }
}
