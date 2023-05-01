package com.tshinow.scala.consumer.application.worker

import com.tshinow.scala.consumer.adapter.sqs.SqsSourceSettings
import com.tshinow.scala.consumer.application.worker.message.MessageCreatedWorker
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.sqs.SqsAsyncClient

class WorkerRunner(sqsAsyncClient: SqsAsyncClient) {

  private val logger = LoggerFactory.getLogger(this.getClass)

  def run(): Unit = {
    val _ = sqsAsyncClient

    println("WorkerRunnerが呼ばれました by println.")

    new MessageCreatedWorker(sqsAsyncClient, SqsSourceSettings(waitTimeSeconds = 1)).run()

    logger.info(s"WorkerRunnerが呼ばれました by logger.")
  }
}
