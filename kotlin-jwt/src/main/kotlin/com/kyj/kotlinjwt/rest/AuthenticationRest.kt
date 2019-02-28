package com.kyj.kotlinjwt.rest

import com.kyj.kotlinjwt.security.JWTUtil
import com.kyj.kotlinjwt.security.PBKDF2Encoder
import com.kyj.kotlinjwt.security.model.AuthRequest
import com.kyj.kotlinjwt.security.model.AuthResponse
import com.kyj.kotlinjwt.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthenticationRest(
  val jwtUtil: JWTUtil,
  val pbkdf2Encoder: PBKDF2Encoder,
  val userService: UserService
) {
  @PostMapping("/login")
  fun login(@RequestBody ar: AuthRequest): Mono<ResponseEntity<AuthResponse>> {
    return userService.findByUsername(ar.username).map {
      if (it.password.equals(pbkdf2Encoder.encode(ar.password))) {
        ResponseEntity.ok(AuthResponse(jwtUtil.generateToken(it)))
      } else {
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<AuthResponse>()
      }
    }.defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<AuthResponse>())
  }
}