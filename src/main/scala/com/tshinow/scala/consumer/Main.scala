package com.tshinow.scala.consumer

import com.tshinow.scala.consumer.adapter.sqs.{ SqsAsyncClientFactory, SqsConsumer, SqsSourceSettings }
import SqsAsyncClientFactory.SqsClientSettings
import org.slf4j.{ Logger, LoggerFactory }
import software.amazon.awssdk.services.sqs.SqsAsyncClient

object Main extends Greeting with App {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  logger.info(s"Consumer Started...")

  println(greeting)

  val clientSettings: SqsAsyncClient = SqsAsyncClientFactory.build(sqsSettings = SqsClientSettings("aaaa"))

  println(clientSettings)

  val sourceSettings = SqsSourceSettings(waitTimeSeconds = 1)

  println(sourceSettings)

  SqsConsumer.consume()
}

trait Greeting {
  lazy val greeting: String = "test！！"
}
