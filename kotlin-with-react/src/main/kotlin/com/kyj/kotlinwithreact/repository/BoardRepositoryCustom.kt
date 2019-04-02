package com.kyj.kotlinwithreact.repository

import com.kyj.kotlinwithreact.domain.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Mono

interface BoardRepositoryCustom {
  fun save(board: Mono<Board>): Mono<Board>
  fun findAllVacation(pageable: Pageable): Page<Board>
}