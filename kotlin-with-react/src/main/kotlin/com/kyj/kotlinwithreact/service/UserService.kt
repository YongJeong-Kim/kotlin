package com.kyj.kotlinwithreact.service

import com.kyj.kotlinwithreact.domain.User
import com.kyj.kotlinwithreact.repository.UserRepositoryCustom
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
  val userRepositoryCustom: UserRepositoryCustom
) {
  fun findById(id: String) = userRepositoryCustom.findById(id)
  fun findByUsername(username: String) = userRepositoryCustom.findByUsername(username)
  fun findAll() = userRepositoryCustom.findAll()
  fun createUser(user: Mono<User>) = userRepositoryCustom.save(user)
}