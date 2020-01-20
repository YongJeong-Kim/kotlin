package com.kyj.kotlingraphql.repository.impl

import com.kyj.kotlingraphql.dto.HobbyDTO
import com.kyj.kotlingraphql.dto.HobbyInput
import com.kyj.kotlingraphql.repository.HobbyRepository
import com.kyj.kotlingraphql.table.Hobbies
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class HobbyRepositoryImpl: HobbyRepository {
  override fun getHobbies() =
    Hobbies.selectAll().map {
      HobbyDTO(
        id = it[Hobbies.id],
        name = it[Hobbies.name],
        description = it[Hobbies.description]
      )
    }

  override fun newHobby(input: HobbyInput) =
    Hobbies.insert {
      it[name] = input.name
      it[description] = input.description
    } get Hobbies.id
}