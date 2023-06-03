package com.tshinow.scala.consumer.tshinow.scala.consumer.usecases

import com.tshinow.scala.consumer.dbadapter.memory.OnMemoryMessageRepository
import com.tshinow.scala.consumer.domain.channel.{ AccountId, ChannelId, MessageId }
import com.tshinow.scala.consumer.usecases.MessagePostedUsecase
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class MessagePostedUsecaseSpec extends AsyncWordSpec with Matchers {

  "MessagePostedUsecase" when {
    "aaaaが指定" should {

      "メッセージが投稿されたイベントから保存＆通知できる" in {
        val usecase = new MessagePostedUsecase(new OnMemoryMessageRepository)

        val _ = usecase.run(MessageId("111"), ChannelId("222"), AccountId("2222"), "")

        succeed
      }

    }
  }
}
