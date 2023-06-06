package com.tshinow.scala.consumer.tshinow.scala.consumer.usecases

import com.tshinow.scala.consumer.domain.channel.{ AccountId, ChannelId, MessageId, MessageRepository, PostedMessage }
import com.tshinow.scala.consumer.usecases.MessagePostedUsecase
import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class MessagePostedUsecaseSpec extends AsyncWordSpec with Matchers with AsyncMockFactory {

  "MessagePostedUsecase" when {
    "aaaaが指定" should {

      "メッセージが投稿されたイベントから保存＆通知できる" in {
        val repository = mock[MessageRepository]

        val message = PostedMessage(MessageId("111"), ChannelId("222"), AccountId("2222"), "")

        (repository.add _).expects(message).once()

        val usecase = new MessagePostedUsecase(repository)

        val _ = usecase.run(MessageId("111"), ChannelId("222"), AccountId("2222"), "")

        succeed
      }

    }
  }
}
