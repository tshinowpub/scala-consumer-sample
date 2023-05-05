package com.tshinow.scala.consumer.adapter.sqs

import org.slf4j.{ Logger, LoggerFactory }
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import zio._

class Consumer(sqsAsyncClient: SqsAsyncClient, settings: SqsSourceSettings) {

  private val source =
    SqsSource("http://localhost:9324/000000000000/message-created", sqsAsyncClient, settings)

  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def consume(): Unit = {
    val stream = source
      .map(message => {
        println("Message が処理されてます！！！")

        println(message)
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
}
