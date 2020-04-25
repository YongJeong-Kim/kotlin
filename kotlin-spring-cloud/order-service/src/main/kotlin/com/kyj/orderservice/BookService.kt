package com.kyj.orderservice

import org.springframework.stereotype.Component

//@Component
class BookService: BookClient {
  override fun ping(): String {
    return "fall"
  }

  override fun books(): List<Book> {
    return listOf(Book(0, "fallback"))
  }
}