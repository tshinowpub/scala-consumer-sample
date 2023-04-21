package com.tshinow.scala.consumer.adapter.sqs

import org.slf4j.{ Logger, LoggerFactory }
import zio._
import zio.{ Task, ZIO }
import zio.stream.ZStream

object SqsConsumer {

  def consume(): Unit = {
    val stream = ZStream
      .fromIterable(1 to 5)
      .mapZIOParUnordered(5)(process)
      .runDrain
      .onError(_ => Console.printLine("Stream application closed! We are doing some cleanup jobs.").orDie)

    println(s"----------------------------------")

    Unsafe.unsafe { implicit unsafe =>
      zio.Runtime.default.unsafe
        .run(
          stream
        ).getOrThrowFiberFailure()
    }

    println("Run consume.")
  }

  private def process(i: Int): Task[Int] = {
    val logger: Logger = LoggerFactory.getLogger(this.getClass)
    logger.info(s"ID: $i の処理中です")

    ZIO.succeed(i)
  }

  /*
  private def receiveMessage(): Unit = {
    val request = ReceiveMessageRequest
      .builder()
      .queueUrl("")
      .waitTimeSeconds(10)
      .maxNumberOfMessages(10)
      .build()
  }*/
}
