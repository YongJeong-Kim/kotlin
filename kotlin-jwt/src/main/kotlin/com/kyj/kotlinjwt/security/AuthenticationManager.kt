package com.kyj.kotlinjwt.security

import com.kyj.kotlinjwt.security.model.Role
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class AuthenticationManager(
  val jwtUtil: JWTUtil
): ReactiveAuthenticationManager {
  override fun authenticate(authentication: Authentication?): Mono<Authentication> {
    val authToken = authentication?.credentials.toString()
    val username = jwtUtil.getUsernameFromToken(authToken)
    username.let {
      jwtUtil.validateToken(authToken).let {
        val claims = jwtUtil.getAllClaimsFromToken(authToken)
        val rolesMap = claims.get("role", List::class.java)
        val roles = mutableListOf<Role>()
        rolesMap.forEach {
          roles.add(Role.valueOf(it.toString()))
        }

        val auth = UsernamePasswordAuthenticationToken(
          username,
          null,
          roles.map { authority -> SimpleGrantedAuthority(authority.name) }.toList()
        )
        return Mono.just(auth)
      }
    }
  }
}