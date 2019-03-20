package com.kyj.kotlinwithreact.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(
  val authenticationManager: AuthenticationManager
): ServerSecurityContextRepository {
  override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun load(exchange: ServerWebExchange?): Mono<SecurityContext> {
    val request = exchange?.request
    val authHeader = request?.headers?.getFirst(HttpHeaders.AUTHORIZATION)

    return if (authHeader != null && authHeader.startsWith("Bearer ")) {
      val authToken = authHeader.substring(7)
      val auth = UsernamePasswordAuthenticationToken(authToken, authToken)
      this.authenticationManager.authenticate(auth).map {
       /* val cookie = ResponseCookie.from("X-Token", authToken)
          .maxAge(3600)
          .build()
        exchange.response.cookies.put("X-Token", listOf(cookie))*/
        SecurityContextImpl(it)
      }
    } else
      Mono.empty()
  }
}