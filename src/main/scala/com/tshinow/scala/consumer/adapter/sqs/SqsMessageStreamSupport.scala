package com.tshinow.scala.consumer.adapter.sqs

import io.circe.Decoder
import org.slf4j.Logger
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import zio.stream.ZStream

trait SqsMessageStreamSupport extends SqsMessageDecodeSupport {
  def sqsSource[A: Decoder](sqsAsyncClient: SqsAsyncClient, queueUrl: String, settings: SqsSourceSettings)(implicit
      logger: Logger
  ): ZStream[Any, Throwable, SqsMessage[A]] = {
    SqsSource(queueUrl, sqsAsyncClient, settings)
      .map { message =>
        decodeMessage[A](message.body()) match {
          case Left(error) => {
            logger.warn(s"Decode failed: {}", error.getMessage)

            SqsMessage[A](message, None)
          }
          case Right(decoded) => {
            logger.info(s"Decode succeeded: {}", decoded)

            SqsMessage[A](message, Some(decoded))
          }
        }
      }
  }
}
