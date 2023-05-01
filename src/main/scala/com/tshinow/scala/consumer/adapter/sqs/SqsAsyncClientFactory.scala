package com.tshinow.scala.consumer.adapter.sqs

import software.amazon.awssdk.auth.credentials.{ AwsBasicCredentials, StaticCredentialsProvider }
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient

import java.net.URI

object SqsAsyncClientFactory {

  def build(sqsSettings: SqsClientSettings): SqsAsyncClient = {
    if (sqsSettings.isLocal) {
      SqsAsyncClient
        .builder()
        .endpointOverride(URI.create("http://localhost:9324"))
        .region(Region.AP_NORTHEAST_1)
        .build()
    } else
      SqsAsyncClient
        .builder()
        .credentialsProvider(
          StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy", "dummy"))
        )
        .region(Region.AP_NORTHEAST_1)
        .build()
  }

  final case class SqsClientSettings(hostName: String, port: Int = 9324, useLocal: Boolean = false) {

    def endpointUrl(): URI = URI.create(s"http://$hostName:$port")

    def isLocal: Boolean = useLocal
  }
}
