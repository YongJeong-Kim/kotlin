package com.kyj.kotlinwithreact.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
open class WebSecurityConfig(
  val authenticationManager: AuthenticationManager,
  val securityContextRepository: SecurityContextRepository
) {
  @Bean
  open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
    http.csrf().disable()
      .formLogin().disable()
      .httpBasic().disable()
      .authenticationManager(authenticationManager)
      .securityContextRepository(securityContextRepository)
      .authorizeExchange()
//      .pathMatchers("/user/**").permitAll()
//      .pathMatchers("/api/users").permitAll()
      .pathMatchers(HttpMethod.OPTIONS).permitAll()
      .pathMatchers("/error/401").permitAll()
      .pathMatchers("/login").permitAll()
//      .pathMatchers("/").permitAll()
      .anyExchange().authenticated()
      .and()
      .exceptionHandling().authenticationEntryPoint(RedirectServerAuthenticationEntryPoint("/error/401"))
//      .exceptionHandling().authenticationEntryPoint(ServerAuthenticationEntryPoint(HttpStatus.FORBIDDEN))
      .and().build()
}