package com.kyj.orderservice

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "book-service", fallback = BookService::class)
interface BookClient {
  @GetMapping("/ping")
  fun ping(): String

  @GetMapping("/books")
  fun books(): List<Book>
}