package com.tshinow.scala.consumer.application.worker.message

import com.tshinow.scala.consumer.adapter.sqs.{ Consumer, SqsSourceSettings }
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.sqs.SqsAsyncClient

class MessageCreatedWorker(sqsAsyncClient: SqsAsyncClient, settings: SqsSourceSettings) {

  private val logger = LoggerFactory.getLogger(getClass)

  private val consumer = new Consumer(sqsAsyncClient, settings)

  def run(): Unit = {
    logger.info("MessageCreatedWorker started.")

    consumer.consume()
  }
}
