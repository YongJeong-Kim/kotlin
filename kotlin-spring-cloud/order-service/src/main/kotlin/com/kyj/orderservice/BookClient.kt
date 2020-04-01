package com.kyj.orderservice

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "book-service")
interface BookClient {
  @GetMapping("/ping")
  fun ping(): String
}