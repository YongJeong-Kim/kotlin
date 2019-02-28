package com.kyj.kotlinjwt.rest

import com.kyj.kotlinjwt.domain.User
import com.kyj.kotlinjwt.security.PBKDF2Encoder
import com.kyj.kotlinjwt.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestRestController(
  val pbkdF2Encoder: PBKDF2Encoder,
  val userService: UserService
) {
  @GetMapping("/test")
  fun test(): String {
    return "test"
  }

  @GetMapping("/save")
  fun save(@RequestBody user: Mono<User>): Mono<User> {
    return userService.save(user.map {
      User(it.username, pbkdF2Encoder.encode(it.password), it.roles)
    })
  }
}