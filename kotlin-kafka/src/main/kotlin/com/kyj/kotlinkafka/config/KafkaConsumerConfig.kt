package com.kyj.kotlinkafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory


@Configuration
@EnableKafka
class KafkaConsumerConfig(
  @Value("\${kafka.consumer.groupId}")
  val groupId: String,
  @Value("\${kafka.server}")
  val server: String
) {
  @Bean
  fun consumerFactory(): ConsumerFactory<String?, String?> {
    val props: MutableMap<String, Any> = HashMap()
    props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = server
    props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    return DefaultKafkaConsumerFactory(props)
  }

  @Bean
  fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String>? {
    val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
    factory.consumerFactory = consumerFactory()
    return factory
  }
}