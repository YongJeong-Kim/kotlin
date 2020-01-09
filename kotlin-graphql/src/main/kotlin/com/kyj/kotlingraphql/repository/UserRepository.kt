package com.kyj.kotlingraphql.repository

import com.kyj.kotlingraphql.dto.UserDTO
import graphql.ExecutionResult
import org.springframework.stereotype.Repository

interface UserRepository: UserRepositoryCustom
interface UserRepositoryCustom {
  fun findAll(): List<UserDTO>
  fun execute(query: String): ExecutionResult
  fun insert(name: String): Int
}