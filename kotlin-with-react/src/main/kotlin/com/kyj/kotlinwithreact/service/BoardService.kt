package com.kyj.kotlinwithreact.service

import com.kyj.kotlinwithreact.domain.Board
import com.kyj.kotlinwithreact.repository.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BoardService(
  val boardRepository: BoardRepository
) {
  fun createBoard(board: Mono<Board>) = boardRepository.save(board)
  fun findAllVacation(pageable: Pageable) = boardRepository.findAllVacation(pageable)
}