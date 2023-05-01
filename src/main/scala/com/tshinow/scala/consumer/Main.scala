package com.tshinow.scala.consumer

import com.tshinow.scala.consumer.adapter.sqs.SqsAsyncClientFactory
import SqsAsyncClientFactory.SqsClientSettings
import com.tshinow.scala.consumer.application.worker.WorkerRunner
import software.amazon.awssdk.services.sqs.SqsAsyncClient

object Main extends App {

  val sqsAsyncClient: SqsAsyncClient =
    SqsAsyncClientFactory.build(sqsSettings = SqsClientSettings(hostName = "localhost", useLocal = true))

  new WorkerRunner(sqsAsyncClient).run()
}
