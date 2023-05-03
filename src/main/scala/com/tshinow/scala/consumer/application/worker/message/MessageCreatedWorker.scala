package com.tshinow.scala.consumer.application.worker.message

import com.tshinow.scala.consumer.adapter.sqs.{ Consumer, SqsSource, SqsSourceSettings }
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import zio.Unsafe

class MessageCreatedWorker(sqsAsyncClient: SqsAsyncClient, sqsSourceSettings: SqsSourceSettings) {

  private val logger = LoggerFactory.getLogger(getClass)

  def run(): Unit = {
    val zStream =
      SqsSource("http://localhost:9324/000000000000/message-created", sqsAsyncClient, sqsSourceSettings)

    val stream = zStream
      .map(message => {
        println("Message が処理されてます！！！")

        println(message)
      })
      .runDrain

    Unsafe.unsafe { implicit unsafe =>
      zio.Runtime.default.unsafe
        .run(stream)
        .getOrThrowFiberFailure()
    }

    logger.info("MessageCreatedWorker started.")

    new Consumer().consume()
  }
}
