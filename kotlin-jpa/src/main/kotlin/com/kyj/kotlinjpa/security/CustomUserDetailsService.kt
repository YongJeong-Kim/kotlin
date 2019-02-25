package com.kyj.kotlinjpa.security

import com.kyj.kotlinjpa.domains.role.RoleRepository
import com.kyj.kotlinjpa.domains.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService (
  val userRepository: UserRepository,
  val roleRepository: RoleRepository
): UserDetailsService {
  override fun loadUserByUsername(username: String?): UserDetails {
    val user = userRepository.findByUsername(username!!.toString())
    user?: throw UsernameNotFoundException("No user present with username: " + username)
    user.let {
      return CustomUserDetails(user, roleRepository.findLoginUserRoles(user.username))
    }
  }
}