package com.kyj.kotlinwithreact.repository.impl

import com.kyj.kotlinwithreact.domain.User
import com.kyj.kotlinwithreact.repository.UserRepositoryCustom
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import reactor.core.publisher.Mono

open class UserRepositoryCustomImpl(val template: ReactiveMongoTemplate): UserRepositoryCustom {
  override fun findById(id: String) = template.findById<User>(id)
  override fun findByUsername(username: String) = template.findOne<User>(Query(where("username").isEqualTo(username)))
  override fun save(user: Mono<User>) = template.save(user)
  override fun findAll() = template.findAll<User>()
  override fun deleteById(id: Int) = template.remove(Query(where("_id").isEqualTo(id)))
}