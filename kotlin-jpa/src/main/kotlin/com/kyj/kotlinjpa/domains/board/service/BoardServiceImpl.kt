package com.kyj.kotlinjpa.domains.board.service

import com.kyj.kotlinjpa.domains.board.entity.Board
import com.kyj.kotlinjpa.domains.board.repository.BoardRepository
import com.kyj.kotlinjpa.domains.board.service.BoardService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(val boardRepository: BoardRepository): BoardService {
  override fun findAll(pageable: Pageable) =
    boardRepository.findAll(PageRequest.of(
      pageable.pageNumber, pageable.pageSize, Sort.by("lastModifiedDate").descending())
    )

  override fun saveBoard(board: Board) = boardRepository.save(board)
}