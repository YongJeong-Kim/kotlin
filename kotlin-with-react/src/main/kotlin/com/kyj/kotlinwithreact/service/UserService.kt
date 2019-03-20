package com.kyj.kotlinwithreact.service

import com.kyj.kotlinwithreact.domain.User
import com.kyj.kotlinwithreact.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
  val userRepository: UserRepository
) {
  fun findById(id: String) = userRepository.findById(id)
  fun findByUsername(username: String) = userRepository.findByUsername(username)
  fun findAll() = userRepository.findAll()
  fun createUser(user: Mono<User>) = userRepository.save(user)
}