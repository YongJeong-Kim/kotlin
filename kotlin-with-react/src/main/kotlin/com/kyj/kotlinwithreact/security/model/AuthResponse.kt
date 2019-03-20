package com.kyj.kotlinwithreact.security.model

import com.kyj.kotlinwithreact.domain.User

class AuthResponse(
  val token: String,
  val user: User? = null
)