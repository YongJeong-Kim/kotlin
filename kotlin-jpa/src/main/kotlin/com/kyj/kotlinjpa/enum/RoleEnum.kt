package com.kyj.kotlinjpa.enum

enum class RoleEnum private constructor(private val roleName: String) {
  ADMIN(RoleName.ROLE_ADMIN), USER(RoleName.ROLE_USER);

  object RoleName {
    const val ROLE_ADMIN = "ROLE_ADMIN"
    const val ROLE_USER = "ROLE_USER"
  }
}