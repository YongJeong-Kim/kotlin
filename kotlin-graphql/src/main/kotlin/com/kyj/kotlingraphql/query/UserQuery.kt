package com.kyj.kotlingraphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.kyj.kotlingraphql.repository.UserRepositoryCustom
import com.kyj.kotlingraphql.table.Hobbies
import com.kyj.kotlingraphql.table.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class UserQuery(
  val userRepository: UserRepositoryCustom
): GraphQLQueryResolver {
  fun getUsers() = transaction { userRepository.findAll() }
  fun getUsersWithHobbies() {
    Users.innerJoin(Hobbies).slice(Users.columns)
  }
}