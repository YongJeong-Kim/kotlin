package com.kyj.kotlingraphql.dto

data class HobbyDTO(
  val id: Int,
  val name: String,
  val description: String
)

data class HobbyInput(
  val name: String,
  val description: String
)