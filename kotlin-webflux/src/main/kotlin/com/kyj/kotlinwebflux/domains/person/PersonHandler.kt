package com.kyj.kotlinwebflux.domains.person

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.http.codec.multipart.Part
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import java.io.File
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
      }

  fun findAll(serverRequest: ServerRequest) = ServerResponse.ok().body(personService.findAll(), Person::class.java)

  fun deleteById(serverRequest: ServerRequest) =
    personService.deleteById(serverRequest.pathVariable("id").toLong())
      .flatMap {
        if (it) ServerResponse.ok().build()
        else ServerResponse.notFound().build()
      }

  fun home(serverRequest: ServerRequest) =
    ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("home")

  fun imageUpload(serverRequest: ServerRequest) =
    serverRequest.body(BodyExtractors.toMultipartData())
      .flatMap { parts ->
        val map: Map<String, Part> = parts.toSingleValueMap()
        val filePart : FilePart = map["myImage"]!! as FilePart
        // Note cast to "FilePart" above

        // Save file to disk - in this example, in the "tmp" folder of a *nix system
        val fileName = filePart.filename()
        filePart.transferTo(File("C:\\Users\\Admin\\Desktop\\$fileName"))

        ServerResponse.ok().body(BodyInserters.fromObject("OK"))
      }
}