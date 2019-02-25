package com.kyj.kotlinjpa.domains.board.controller

import com.kyj.kotlinjpa.domains.board.service.BoardService
import com.kyj.kotlinjpa.domains.board.entity.Board
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(val boardService: BoardService) {
  @GetMapping("/board")
  fun board(pageable: Pageable) = boardService.findAll(pageable)

  @PostMapping("/board")
  fun saveBoard(@RequestBody board: Board) = boardService.saveBoard(board)
}