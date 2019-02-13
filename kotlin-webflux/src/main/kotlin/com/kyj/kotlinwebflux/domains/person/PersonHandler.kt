package com.kyj.kotlinwebflux.domains.person

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
data class PersonHandler(val personService: PersonService) {
  fun findById(serverRequest: ServerRequest) =
    personService.findById(serverRequest.pathVariable("id").toLong())
      .flatMap { ServerResponse.ok().body(BodyInserters.fromObject(it)) }
      .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())

  fun save(serverRequest: ServerRequest) =
    personService.save(serverRequest.bodyToMono())
      .flatMap {
        ServerResponse.created(URI.create("/person/${it.id}")).build()
      }.doOnError {
        ServerResponse.badRequest()
      }

  fun findAll(serverRequest: ServerRequest) = ServerResponse.ok().body(personService.findAll(), Person::class.java)

  fun deleteById(serverRequest: ServerRequest) =
    personService.deleteById(serverRequest.pathVariable("id").toLong())
      .flatMap {
        if (it) ServerResponse.ok().build()
        else ServerResponse.notFound().build()
      }
}