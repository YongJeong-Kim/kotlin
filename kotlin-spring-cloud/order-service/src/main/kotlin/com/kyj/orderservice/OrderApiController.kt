package com.kyj.orderservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(
  val bookClient: BookClient
) {
  @GetMapping
  fun book() = bookClient.ping()
}