package com.kyj.kotlingraphql.mutation

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.kyj.kotlingraphql.repository.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class UserMutation(
  val userRepository: UserRepository
): GraphQLMutationResolver {
  fun newUser(name: String) = transaction { userRepository.insert(name) }
}
