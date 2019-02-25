package com.kyj.kotlinjpa.domains.board.repository

import com.kyj.kotlinjpa.domains.board.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
}