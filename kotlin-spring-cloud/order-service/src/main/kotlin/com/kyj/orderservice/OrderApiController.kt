package com.kyj.orderservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class OrderApiController(
  val bookClient: BookClient,
  val orderService: OrderService
) {
  @GetMapping("/")
  fun ping() = bookClient.ping()

  @GetMapping("/books")
  fun books() = bookClient.books()

  @GetMapping("/test")
  fun test() = "test string"

  @GetMapping(value = ["/get"], produces = ["application/json"])
  fun getOrder(): List<String> = orderService.getOrder()
}