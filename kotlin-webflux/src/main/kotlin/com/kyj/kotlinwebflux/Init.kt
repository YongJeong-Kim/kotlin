package com.kyj.kotlinwebflux

import com.kyj.kotlinwebflux.domains.person.Person
import com.kyj.kotlinwebflux.domains.person.PersonService
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import javax.annotation.PostConstruct

@Component
class Init(val mongoOperations: ReactiveMongoOperations, val personService: PersonService) {
  @PostConstruct
  fun init() {
    mongoOperations.collectionExists("person").subscribe {
      if (it) println("exist collection")
      else mongoOperations.createCollection("person").subscribe {
        val persons = listOf(
          Person(1L, "aaa", Person.Address("01011112222", "33333")),
          Person(2L, "bbb", Person.Address("01033334444", "55555")))
        persons.map(Person::toMono).map(personService::save).map(Mono<Person>::subscribe)
        }
    }
  }
}