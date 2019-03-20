package com.kyj.kotlinwithreact.repository

import com.kyj.kotlinwithreact.domain.User
import com.mongodb.client.result.DeleteResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepositoryCustom {
  fun findById(id: String): Mono<User>
  fun findByUsername(username: String): Mono<User>
  fun save(user: Mono<User>): Mono<User>
  fun findAll(): Flux<User>
  fun deleteById(id: Int): Mono<DeleteResult>
}