package com.kyj.kotlinoauth2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
  @GetMapping("/")
  fun home() = "home"

  @GetMapping("/request")
  fun request() = "request"
}