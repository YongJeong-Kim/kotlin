package com.kyj.bookservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/api")
class TestController(
  @Value("\${spring.profiles}")
  val profile: String
) {
  @GetMapping("/ping")
  fun ping() = "zone is $profile"

  @GetMapping("/books")
  fun books() = listOf(
    Book(1, "aaa title"),
    Book(2, "bbb title"),
    Book(3, "ccc title"),
    Book(4, "ddd title"),
    Book(5, "eee title")
  )

}