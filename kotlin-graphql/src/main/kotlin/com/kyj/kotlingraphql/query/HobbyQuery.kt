package com.kyj.kotlingraphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.kyj.kotlingraphql.dto.HobbyDTO
import com.kyj.kotlingraphql.repository.HobbyRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class HobbyQuery(
  val hobbyRepository: HobbyRepository
): GraphQLQueryResolver {
  fun getHobbies() = transaction { hobbyRepository.getHobbies() }
}