package com.kyj.kotlingraphql.repository.impl

import com.kyj.kotlingraphql.dto.UserDTO
import com.kyj.kotlingraphql.repository.UserRepository
import com.kyj.kotlingraphql.repository.UserRepositoryCustom
import com.kyj.kotlingraphql.table.Users
import graphql.ExecutionResult
import graphql.GraphQL
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl: UserRepository, UserRepositoryCustom {
  lateinit var graphQL: GraphQL

  override fun execute(query: String): ExecutionResult =
    graphQL.execute(query)

  override fun insert(name: String) =
    Users.insert {
      it[Users.name] = name
    } get Users.id

  override fun findAll() =
    Users.selectAll()
      .map {
        UserDTO(
          id = it[Users.id],
          name = it[Users.name] ?: "no name",
          age = it[Users.age]
        )
      }
}