package com.kyj.kotlinwebflux.domains.person

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.remove
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class PersonRepository(val mongoTemplate: ReactiveMongoTemplate) {
  fun findById(id: Long) = mongoTemplate.findById<Person>(id) // or findById(id, Person::class.java)
  fun save(person: Mono<Person>) = mongoTemplate.save(person)
  fun findAll() = mongoTemplate.findAll<Person>() // or findAll(Person::class.java)
  fun deleteById(id: Long) = mongoTemplate.remove<Person>(Query(where("_id").isEqualTo(id))) // or remove(Query(where("_id").isEqualTo(id)), Person::class.java)
}