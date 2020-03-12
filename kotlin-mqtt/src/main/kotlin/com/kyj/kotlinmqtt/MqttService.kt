package com.kyj.kotlinmqtt

import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service

@Service
class MqttService(
  val mqttOutFlow: IntegrationFlow
) {
  fun receive(msg: Message<*>) {
    val topic = msg.headers["mqtt_receivedTopic"].toString()
    val payload = msg.payload
  }

  fun send(payload: String) =
    mqttOutFlow.inputChannel.send(GenericMessage(payload))
}