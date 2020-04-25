package com.kyj.orderservice

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Component
class OrderService {
  @HystrixCommand(
    fallbackMethod = "getOrderFallback",
    commandProperties = [HystrixProperty(
      name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")])
  fun getOrder(): List<String> {
    val random = Random().nextInt((3 - 1) + 1) + 1
    if (random == 3)
      Thread.sleep(12000)
    return listOf("list1", "list2")
  }

  private fun getOrderFallback(): List<String> = listOf("getOrder fallback")
}