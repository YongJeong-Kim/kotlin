package com.kyj.bookservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
  @Value("\${spring.profiles}")
  val profile: String
) {
  @GetMapping("/ping")
  fun ping() = "zone is $profile"
}