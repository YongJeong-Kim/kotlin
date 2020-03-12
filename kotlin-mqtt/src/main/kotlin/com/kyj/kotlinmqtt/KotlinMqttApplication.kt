package com.kyj.kotlinmqtt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMqttApplication

fun main(args: Array<String>) {
  runApplication<KotlinMqttApplication>(*args)
}
