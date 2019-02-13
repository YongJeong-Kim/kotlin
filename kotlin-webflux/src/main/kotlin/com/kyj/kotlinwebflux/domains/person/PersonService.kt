package com.kyj.kotlinwebflux.domains.person

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PersonService {
  fun findById(id: Long): Mono<Person>
  fun save(person: Mono<Person>): Mono<Person>
  fun findAll(): Flux<Person>
  fun deleteById(id: Long): Mono<Boolean>
}