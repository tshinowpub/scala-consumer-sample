package com.tshinow.scala.consumer.adapter.sqs

import io.circe.parser._
import io.circe.{ Decoder, Error }

trait SqsMessageDecodeSupport {
  def decodeMessage[A: Decoder](message: String): Either[Error, A] =
    decode[A](message)
}
