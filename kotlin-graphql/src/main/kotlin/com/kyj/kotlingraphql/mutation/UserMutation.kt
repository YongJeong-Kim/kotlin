package com.kyj.kotlingraphql.mutation

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.kyj.kotlingraphql.repository.UserRepositoryCustom
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class UserMutation(
  val userRepositoryCustom: UserRepositoryCustom
): GraphQLMutationResolver {
  fun newUser(name: String) = transaction { userRepositoryCustom.insert(name) }
}
