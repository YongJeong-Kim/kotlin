package com.kyj.authenticationservice

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
  @GetMapping(value = ["/user"], produces = ["application/json"])
  fun user(user: OAuth2Authentication): Map<String, Any> {
    val map = mapOf("user" to user.userAuthentication.principal,
      "authorities" to AuthorityUtils.authorityListToSet(user.userAuthentication.authorities))
    return map
  }
}