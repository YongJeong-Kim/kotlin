package com.kyj.kotlingraphql.mutation

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.kyj.kotlingraphql.dto.HobbyInput
import com.kyj.kotlingraphql.repository.HobbyRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class HobbyMutation(
  val hobbyRepository: HobbyRepository
): GraphQLMutationResolver {
  fun newHobby(input: HobbyInput) = transaction { hobbyRepository.newHobby(input) }
}