package com.kyj.kotlinmqtt

import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.Pollers
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec
import org.springframework.integration.endpoint.MessageProducerSupport
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.integration.stream.CharacterStreamReadingMessageSource
import org.springframework.messaging.MessageHandler
import java.util.*
import java.util.function.Consumer

@Configuration
class MqttConfig(
  @Value("\${mqtt.ip}")
  val ip: String,
  @Value("\${mqtt.port}")
  val port: String,
  @Value("\${mqtt.username}")
  val username: String,
  @Value("\${mqtt.password}")
  val password: String,
  val mqttService: MqttService
) {
  @Bean
  fun mqttClientFactory(): MqttPahoClientFactory {
    val factory = DefaultMqttPahoClientFactory()
    val options = MqttConnectOptions()
    options.serverURIs = arrayOf("""tcp://$ip:$port""")
    options.userName = username
    options.password = password.toCharArray()
    factory.connectionOptions = options
    return factory
  }

  // consumer
  @Bean
  fun mqttInFlow(): IntegrationFlow {
    return IntegrationFlows.from(mqttInbound())
      .transform { p: Any -> p }
//      .handle(logger())
      .handle { msg ->
        //        println(msg.headers["mqtt_receivedTopic"])
//        println(msg.payload)
        mqttService.receive(msg)
//        return m or Unit
      }.get()
  }

  @Bean
  fun mqttInbound(): MessageProducerSupport {
    /*val adapter = MqttPahoMessageDrivenChannelAdapter("siSampleConsumer",
      mqttClientFactory(), "siSampleTopic")*/
    val adapter = MqttPahoMessageDrivenChannelAdapter(UUID.randomUUID().toString(),
      mqttClientFactory(), "#")
    adapter.setCompletionTimeout(5000)
    adapter.setConverter(DefaultPahoMessageConverter())
    adapter.setQos(1)
    return adapter
  }

  // producer
  @Bean
  fun mqttOutFlow(): IntegrationFlow =
    IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
      Consumer { e: SourcePollingChannelAdapterSpec -> e.poller(Pollers.fixedDelay(1000)) })
      .transform { p: Any -> "$p sent to MQTT" }
      .handle(mqttOutbound())
      .get()

  @Bean
  fun mqttOutbound(): MessageHandler {
    val messageHandler = MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory())
    messageHandler.setAsync(true)
    messageHandler.setDefaultTopic("siSampleTopic")
    return messageHandler
  }
}