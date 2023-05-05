package com.tshinow.scala.consumer.adapter.sqs

import software.amazon.awssdk.services.sqs.model.{ Message, ReceiveMessageRequest }
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import zio.Console.printLine
import zio.ZIO
import zio.stream.ZStream

import scala.concurrent.duration._
import scala.compat.java8.FutureConverters.CompletionStageOps
import scala.concurrent.Await
import scala.jdk.CollectionConverters._

object SqsSource {

  def apply(queueUrl: String, client: SqsAsyncClient, settings: SqsSourceSettings): ZStream[Any, Throwable, Message] = {

    /** @see
      *   https://zio.dev/reference/stream/zstream/resourceful-streams/
      */
    val stream: ZStream[Any, Throwable, Message] =
      ZStream
        .acquireReleaseWith(
          ZIO.attempt(client.receiveMessage(createReceiveMessageRequest(queueUrl, settings)).toScala) <* printLine(
            "SQS consume started."
          )
        )(future => ZIO.succeed(Await.result(future, 3.second)) <* printLine("Message not found.").orDie)
        .flatMap { future =>
          ZStream.fromIterator(Await.result(future, 3.second).messages().asScala.iterator)
        }

    stream
  }

  private def createReceiveMessageRequest(queueUrl: String, settings: SqsSourceSettings): ReceiveMessageRequest = {
    ReceiveMessageRequest
      .builder()
      .queueUrl(queueUrl)
      .waitTimeSeconds(settings.waitTimeSeconds)
      .attributeNamesWithStrings("all")
      .maxNumberOfMessages(settings.maxNumberOfMessages)
      .build()
  }
}
