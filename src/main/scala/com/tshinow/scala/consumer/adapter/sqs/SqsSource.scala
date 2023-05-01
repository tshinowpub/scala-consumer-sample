package com.tshinow.scala.consumer.adapter.sqs

import software.amazon.awssdk.services.sqs.model.{ ReceiveMessageRequest, ReceiveMessageResponse, SqsException }
import software.amazon.awssdk.services.sqs.SqsAsyncClient

import scala.compat.java8.FutureConverters.CompletionStageOps
import scala.concurrent.Future

object SqsSource {

  def apply(
      queueUrl: String,
      client: SqsAsyncClient,
      settings: SqsSourceSettings
  ): Future[ReceiveMessageResponse] = {

    try {
      val messages = client.receiveMessage(createReceiveMessageRequest(queueUrl, settings)).toScala

      println(messages)

      return messages
    } catch {
      case error: SqsException => println(error.toString)
      case _: Throwable        => None
    }

    val aaa: Future[ReceiveMessageResponse] = Future.successful(null)

    aaa
  }

  private def createReceiveMessageRequest(queueUrl: String, settings: SqsSourceSettings): ReceiveMessageRequest = {
    ReceiveMessageRequest
      .builder()
      .queueUrl(queueUrl)
      .waitTimeSeconds(settings.waitTimeSeconds)
      .attributeNamesWithStrings("all")
      .maxNumberOfMessages(settings.waitTimeSeconds)
      .build()
  }
}
