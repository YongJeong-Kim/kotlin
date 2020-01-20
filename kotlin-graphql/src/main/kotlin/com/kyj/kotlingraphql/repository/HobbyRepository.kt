package com.kyj.kotlingraphql.repository

import com.kyj.kotlingraphql.dto.HobbyDTO
import com.kyj.kotlingraphql.dto.HobbyInput

interface HobbyRepository: HobbyRepositoryCustom
interface HobbyRepositoryCustom {
  fun getHobbies(): List<HobbyDTO>
  fun newHobby(input: HobbyInput): Int
}