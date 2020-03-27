package com.kyj.kotlinkafka

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.TopicPartition
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class TestController(
  val kafkaTemplate: KafkaTemplate<String, String>
) {
  @GetMapping("/producer")
  fun producer() {
    val conf = Properties()
    conf.setProperty("bootstrap.servers", "localhost:9092")
    conf.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    conf.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = KafkaProducer<Int, String>(conf)

    val record = ProducerRecord("test", 1, "hihi")

    producer.send(record) { metadata, exception ->
      if (metadata != null) {
        println("${metadata.partition()}, ${metadata.offset()}")
      } else {
        println("fail")
      }
    }
    producer.close()
  }

  @GetMapping("/producer2/{msg}")
  fun producer2(@PathVariable msg: String) {
    kafkaTemplate.send("test", "1", msg)
  }
}