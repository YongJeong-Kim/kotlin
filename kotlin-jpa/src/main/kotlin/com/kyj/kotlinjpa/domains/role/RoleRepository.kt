package com.kyj.kotlinjpa.domains.role

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RoleRepository: JpaRepository<Role, Long> {
  @Query(value =
    "select r.name from User u " +
    "join user_role ur on u.id = ur.user_id " +
    "join role r on r.id = ur.role_id " +
    "where u.username = ?1", nativeQuery = true)
  fun findLoginUserRoles(username: String): MutableList<String>
}