package com.tshinow.scala.consumer.adapter.sqs

import com.tshinow.scala.consumer.domain.channel.{ AccountId, ChannelId, MessageCreatedEvent, MessageId, MessageType }
import MessageCreatedEvent.{ MessagePosted, UnSupportedEvent, UserAdded }
import com.tshinow.scala.consumer.dbadapter.mysql.MysqlMessageRepository
import com.tshinow.scala.consumer.usecases.MessagePostedUsecase
import io.circe.{ Decoder, HCursor }
import org.slf4j.{ Logger, LoggerFactory }
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import zio._
import zio.stream.ZStream

class Consumer(sqsAsyncClient: SqsAsyncClient, settings: SqsSourceSettings)
    extends SqsMessageStreamSupport
    with SqsMessageDecodeSupport {

  implicit private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  private val source: ZStream[Any, Throwable, SqsMessage[MessageCreatedEvent]] =
    sqsSource[MessageCreatedEvent](sqsAsyncClient, "http://localhost:9324/000000000000/message-created", settings)

  implicit private def messageCreatedEvent: Decoder[MessageCreatedEvent] = (c: HCursor) =>
    for {
      messageId       <- c.downField("messageId").as[String]
      channelId       <- c.downField("channelId").as[String]
      accountId       <- c.downField("accountId").as[String]
      addedAccountIds <- c.downField("addedAccountIds").as[Option[Vector[String]]]
      message_type    <- c.downField("messageType").as[String]
      body            <- c.downField("body").as[String]
    } yield {
      MessageType.withName(message_type) match {
        case MessageType.UserPosted =>
          MessagePosted(MessageId(messageId), ChannelId(channelId), AccountId(accountId), body)
        case MessageType.UserAdded if addedAccountIds.nonEmpty =>
          UserAdded(
            MessageId(messageId),
            ChannelId(channelId),
            AccountId(accountId),
            addedAccountIds.get.map(AccountId),
            body
          )
        case _ => UnSupportedEvent(body)
      }
    }

  def consume(): Unit = {
    val stream = source
      .map(message => {
        println("Message が処理されてます！！！")

        message.decodedMessage.map(handler)

        println(message)

        message
      })
      .runDrain
      .onError(_ => Console.printLine("Stream application closed! We are doing some cleanup jobs.").orDie)

    Unsafe.unsafe { implicit unsafe =>
      zio.Runtime.default.unsafe
        .run(
          stream
        ).getOrThrowFiberFailure()
    }

    println("Run consume.")

    logger.info("Run consume by logger.")
  }

  private val handler: MessageCreatedEvent => MessageCreatedEvent = {
    case MessagePosted(messageId, channelId, accountId, body) => {
      println("MessagePosted")

      new MessagePostedUsecase(new MysqlMessageRepository).run(messageId, channelId, accountId, body)

      MessagePosted(messageId, channelId, accountId, body)
    }

    case _ => {
      println("それ以外が呼ばれました")

      UnSupportedEvent("")
    }
  }
}
