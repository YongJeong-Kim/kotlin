package com.kyj.kotlinwithreact.repository

import com.kyj.kotlinwithreact.domain.User
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserRepositoryCustom(val template: ReactiveMongoTemplate) {
  fun findById(id: String) = template.findById<User>(id)
  fun findByUsername(username: String) = template.findOne<User>(Query(where("username").isEqualTo(username)))
  fun save(user: Mono<User>) = template.save(user)
  fun findAll() = template.findAll<User>()
  fun deleteById(id: Int) = template.remove(Query(where("_id").isEqualTo(id)))
}