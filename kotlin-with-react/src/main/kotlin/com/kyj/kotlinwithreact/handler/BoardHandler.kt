package com.kyj.kotlinwithreact.handler

import com.kyj.kotlinwithreact.service.BoardService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BoardHandler(val boardService: BoardService) {
  fun findAllVacation(serverRequest: ServerRequest): Mono<ServerResponse> {
    val page = serverRequest.queryParam("page").map { it.toInt() }.orElseGet { 0 }
    val size = serverRequest.queryParam("size").map { it.toInt() }.orElseGet { 5 }
    return ServerResponse.ok().body(BodyInserters.fromObject(boardService.findAllVacation(PageRequest.of(page, size))))
  }
}