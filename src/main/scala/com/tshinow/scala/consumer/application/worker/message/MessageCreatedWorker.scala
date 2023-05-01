package com.tshinow.scala.consumer.application.worker.message

import com.tshinow.scala.consumer.adapter.sqs.{ Consumer, SqsSource, SqsSourceSettings }
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.sqs.SqsAsyncClient

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.jdk.CollectionConverters._

class MessageCreatedWorker(sqsAsyncClient: SqsAsyncClient, sqsSourceSettings: SqsSourceSettings) {

  private val logger = LoggerFactory.getLogger(getClass)

  def run(): Unit = {
    val sqsFuture = SqsSource(
      "http://localhost:9324/000000000000/message-created",
      sqsAsyncClient,
      sqsSourceSettings
    )

    Await.result(
      sqsFuture.map(response => {
        val messages = response.messages().asScala.toList

        println(messages)

        println("メッセージ受信後です！！！")
      }),
      Duration.Inf
    )

    logger.info("MessageCreatedWorker started.")

    new Consumer().consume()
  }
}
