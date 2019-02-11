package com.kyj.kotlinjpa

import com.kyj.kotlinjpa.entity.Person
import com.kyj.kotlinjpa.entity.Team
import com.kyj.kotlinjpa.repository.PersonRepository
import com.kyj.kotlinjpa.repository.TeamRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class HomeController(val personRepository: PersonRepository, val teamRepository: TeamRepository) {
  @GetMapping("/persons")
  fun persons(): Flux<Person> {
    return Flux.fromIterable(personRepository.findAll())
  }

  @GetMapping("/teams")
  @Transactional
  fun teams(): Flux<Team> {
    return Flux.fromIterable(teamRepository.findAll())
  }

  @PostMapping("/persons")
  fun savePerson(person: Person): Person {
    println(person.name)
    return personRepository.save(person)
}

  @PostMapping("/teams")
  fun saveTeam(team: Team): Team {
    println(team.name)
    return teamRepository.save(team)
  }
}