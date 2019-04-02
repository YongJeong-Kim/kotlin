package com.kyj.kotlinwithreact.repository

import com.kyj.kotlinwithreact.domain.Board
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface BoardRepository: ReactiveMongoRepository<Board, String>, BoardRepositoryCustom {
}