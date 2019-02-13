package com.kyj.kotlinwebflux.domains.person

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PersonServiceImpl(val personRepository: PersonRepository): PersonService {
  override fun deleteById(id: Long) = personRepository.deleteById(id).map { it.deletedCount > 0 }
  override fun findAll(): Flux<Person> = personRepository.findAll()
  override fun save(person: Mono<Person>) = personRepository.save(person)
  override fun findById(id: Long) = personRepository.findById(id)
}