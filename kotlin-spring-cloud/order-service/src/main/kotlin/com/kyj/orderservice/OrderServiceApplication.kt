package com.kyj.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.Duration


@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@RibbonClient(name = "book-service", configuration = [RibbonConfig::class])
@SpringBootApplication
class OrderServiceApplication {
  @LoadBalanced
  @Bean
  fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate? {
    return restTemplateBuilder
      .setConnectTimeout(Duration.ofMillis(1000))
      .setReadTimeout(Duration.ofMillis(1000))
      .build()
  }
}

fun main(args: Array<String>) {
  runApplication<OrderServiceApplication>(*args)
}
