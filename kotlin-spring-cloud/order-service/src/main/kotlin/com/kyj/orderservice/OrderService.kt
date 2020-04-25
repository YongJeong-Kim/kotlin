package com.kyj.orderservice

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono.delay
import java.util.*

@Service
class OrderService {
  @HystrixCommand(
    fallbackMethod = "getOrderFallback",
    commandProperties = [HystrixProperty(
      name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")])
  fun getOrder(): List<String> {
    sleep()
    return listOf("list1", "list2")
  }
  private fun sleep() {
    val random = Random().nextInt((3 - 1) + 1) + 1
    if (random == 3) {
      Thread.sleep(32000L)
    }
  }

  private fun getOrderFallback(): List<String> = listOf("getOrder fallback")
}