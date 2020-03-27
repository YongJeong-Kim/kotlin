package com.kyj.kotlinkafka

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class KotlinKafkaApplicationTests {

  @Test
  fun contextLoads() {
  }

  @Test
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

}
