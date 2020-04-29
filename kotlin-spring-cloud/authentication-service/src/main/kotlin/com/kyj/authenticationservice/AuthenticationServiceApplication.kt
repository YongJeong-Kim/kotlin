package com.kyj.authenticationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
class AuthenticationServiceApplication

fun main(args: Array<String>) {
  runApplication<AuthenticationServiceApplication>(*args)
}
