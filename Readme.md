# OverView

![Workflow Status](https://github.com/tshinowpub/scala-consumer-sample/workflows/test/badge.svg)

Scala's worker for queue sample. Supported queues include.

- Amazon SQS
- Amazon Kinesis

## Create data

- Check queue list

```shell
$ aws sqs list-queues --endpoint-url http://localhost:9324/
```

- Create queue

```shell
$ aws sqs create-queue --queue-name message-created --attributes ReceiveMessageWaitTimeSeconds=20 --endpoint-url http://localhost:9324/
```

- Send message

```shell
// Simple text message
$ aws sqs send-message --endpoint-url http://localhost:9324/ --queue-url "http://localhost:9324/000000000000/message-created" --message-body "テストJsonメッセージだよ！！！"

// Json message

// UserPosted Event
$ aws sqs send-message --endpoint-url http://localhost:9324/ --queue-url "http://localhost:9324/000000000000/message-created" --message-body '{"messageId": "01H0RMAHGSWNW4QEZSCRPSKT4Y", "channelId": "01H0RMAHGSB2ZGQD4KTJ06RDQH", "accountId": "999", "messageType": "UserPosted", "body": "テストですよ！"}'

// UserAdded Event
$ aws sqs send-message --endpoint-url http://localhost:9324/ --queue-url "http://localhost:9324/000000000000/message-created" --message-body '{"messageId": "01H0RMAHGSWNW4QEZSCRPSKT4Y", "channelId": "01H0RMAHGSB2ZGQD4KTJ06RDQH", "accountId": "999", "messageType": "UserPosted", "addedAccountIds": ["222", "333"], "body": "テストですよ！"}'
```
