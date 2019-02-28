package com.kyj.kotlinjwt.repository

import com.kyj.kotlinjwt.domain.User
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserRepository(
  val template: ReactiveMongoTemplate
) {
  fun save(user: Mono<User>) = template.save(user)
  fun findByUsername(username: String) = template.find<User>(Query(where("username").isEqualTo(username)))
}