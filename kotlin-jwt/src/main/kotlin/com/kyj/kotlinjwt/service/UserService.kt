package com.kyj.kotlinjwt.service

import com.kyj.kotlinjwt.domain.User
import com.kyj.kotlinjwt.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class UserService(
  val userRepository: UserRepository
) {
  fun findByUsername(username: String): Mono<User> = userRepository.findByUsername(username).toMono()
  fun save(user: Mono<User>): Mono<User> = userRepository.save(user)
}