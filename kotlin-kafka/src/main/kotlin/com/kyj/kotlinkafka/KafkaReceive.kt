package com.kyj.kotlinkafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaReceive {
  @KafkaListener(topics = ["test"], groupId = "foo")
  fun receive(consumerRecord: ConsumerRecord<*, *>) {
    println(consumerRecord)
  }
}