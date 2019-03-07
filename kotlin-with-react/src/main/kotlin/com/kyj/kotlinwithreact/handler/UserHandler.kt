package com.kyj.kotlinwithreact.handler

import com.kyj.kotlinwithreact.domain.User
import com.kyj.kotlinwithreact.security.JWTUtil
import com.kyj.kotlinwithreact.security.PBKDF2Encoder
import com.kyj.kotlinwithreact.security.model.AuthResponse
import com.kyj.kotlinwithreact.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty
import reactor.core.publisher.toMono

@Component
class UserHandler(
  val userService: UserService,
  val pbkdf2Encoder: PBKDF2Encoder,
  val jwtUtil: JWTUtil
) {
  fun findById(serverRequest: ServerRequest) =
    userService.findById(serverRequest.pathVariable("id"))
      .flatMap { ServerResponse.ok().body(BodyInserters.fromObject(it)) }
      .switchIfEmpty { ServerResponse.status(HttpStatus.NOT_FOUND).build() }

  fun findAll(serverRequest: ServerRequest) = ServerResponse.ok().body(userService.findAll(), User::class.java)

  fun login(serverRequest: ServerRequest): Mono<ServerResponse> {
    val user = serverRequest.bodyToMono(User::class.java)
    return user.flatMap {
      userService.findByUsername(it.username).toMono().flatMap {db ->
        if (db.password == pbkdf2Encoder.encode(it.password))
          ServerResponse.ok().body(BodyInserters.fromObject(AuthResponse(jwtUtil.generateToken(it))))
        else
          ServerResponse.status(HttpStatus.UNAUTHORIZED).build()
      }
    }.switchIfEmpty { ServerResponse.status(HttpStatus.UNAUTHORIZED).body(BodyInserters.fromObject("invalid info")) }
  }

  fun unauthorized(serverRequest: ServerRequest) =
    ServerResponse.status(HttpStatus.UNAUTHORIZED).body(BodyInserters.fromObject("unauthorized"))
}

