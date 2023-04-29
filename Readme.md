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
