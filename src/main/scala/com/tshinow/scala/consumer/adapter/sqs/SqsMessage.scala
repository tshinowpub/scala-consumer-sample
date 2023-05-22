package com.tshinow.scala.consumer.adapter.sqs

import software.amazon.awssdk.services.sqs.model.Message

case class SqsMessage[A](sqsMessage: Message, decodedMessage: Option[A]) {}

object SqsMessage {
  def withTimestamp[A](decodedMessage: Option[A], sqsMessage: Message): SqsMessage[A] = {

    SqsMessage(sqsMessage, decodedMessage)
  }
}
