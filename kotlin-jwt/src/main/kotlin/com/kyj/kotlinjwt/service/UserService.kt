package com.kyj.kotlinjwt.service

import com.kyj.kotlinjwt.domain.User
import com.kyj.kotlinjwt.repository.UserRepository
import com.kyj.kotlinjwt.security.model.Role
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
  val userRepository: UserRepository
) {
  private val userUsername = "user"// password: user
  private val user = User(userUsername, "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", listOf(Role.ROLE_USER))

  //username:passwowrd -> admin:admin
  private val adminUsername = "admin"// password: admin
  private val admin = User(adminUsername, "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", listOf(Role.ROLE_ADMIN))

  fun findByUsername(username: String): Mono<User> {
    return if (username == userUsername) {
      Mono.just(user)
    } else if (username == adminUsername) {
      Mono.just(admin)
    } else {
      Mono.empty()
    }
  }

  fun save(user: Mono<User>): Mono<User> = userRepository.save(user)
}