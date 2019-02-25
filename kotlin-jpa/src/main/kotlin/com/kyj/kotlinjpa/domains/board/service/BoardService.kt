package com.kyj.kotlinjpa.domains.board.service

import com.kyj.kotlinjpa.domains.board.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardService {
  fun saveBoard(board: Board): Board
  fun findAll(pageable: Pageable): Page<Board>
}