package com.kyj.kotlinwithreact.repository.impl

import com.kyj.kotlinwithreact.domain.Board
import com.kyj.kotlinwithreact.repository.BoardRepositoryCustom
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.support.PageableExecutionUtils
import reactor.core.publisher.Mono
import java.util.function.LongSupplier

class BoardRepositoryCustomImpl(
  val reactiveMongoTemplate: ReactiveMongoTemplate,
  val mongoTemplate: MongoTemplate
): BoardRepositoryCustom {
  override fun findAllVacation(pageable: Pageable): Page<Board> {
//    val boards = template.findAll(Board::class.java).toIterable().toList()
//    val boards = mongoTemplate.findAll(Board::class.java)

    /*return template.count(Query(), Board::class.java).doOnNext {
      PageableExecutionUtils.getPage(
        boards,
        pageable,
        it!!)
    }*/
//    val ww = LongSupplier { 10 }
    return PageableExecutionUtils.getPage(
      mongoTemplate.find(Query().with(pageable), Board::class.java),
      PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by("updateDate").descending()),
      LongSupplier { mongoTemplate.count(Query(), Board::class.java) })
  }

  override fun save(board: Mono<Board>) = reactiveMongoTemplate.save<Board>(board)
}