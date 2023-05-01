package com.tshinow.scala.consumer.adapter.sqs

import scala.concurrent.duration.FiniteDuration

final case class SqsSourceSettings private (
    waitTimeSeconds: Int = 10,
    maxNumberOfMessages: Int = 10,
    visibilityTimeout: Option[FiniteDuration] = None
) {
  override def toString: String =
    "SqsSourceSettings(" +
    s"waitTimeSeconds=$waitTimeSeconds, " +
    s"maxNumberOfMessages=$maxNumberOfMessages, " +
    s"visibilityTimeout=${visibilityTimeout.map(_.toCoarsest)}" +
    ")"
}
