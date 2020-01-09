package com.kyj.kotlingraphql.controller.rest

import com.kyj.kotlingraphql.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
  @PostMapping
  fun query(@RequestBody query: String) {

  }
}