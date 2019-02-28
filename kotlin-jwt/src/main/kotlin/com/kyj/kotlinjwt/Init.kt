package com.kyj.kotlinjwt

import com.kyj.kotlinjwt.domain.User
import com.kyj.kotlinjwt.security.PBKDF2Encoder
import com.kyj.kotlinjwt.security.model.Role
import com.kyj.kotlinjwt.service.UserService
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import javax.annotation.PostConstruct

@Component
class Init(
  val operations: ReactiveMongoOperations,
  val pbkdF2Encoder: PBKDF2Encoder,
  val userService: UserService) {
  @PostConstruct
  fun init() {
    operations.collectionExists("user").subscribe {
      if (it) println("exist collection")
      else operations.createCollection("user").subscribe {
        val persons = listOf(
          User("user", pbkdF2Encoder.encode("user"), listOf(Role.ROLE_USER)),
          User("admin", pbkdF2Encoder.encode("admin"), listOf(Role.ROLE_ADMIN)))
        persons.map(User::toMono).map(userService::save).map(Mono<User>::subscribe)
      }
    }
  }
}