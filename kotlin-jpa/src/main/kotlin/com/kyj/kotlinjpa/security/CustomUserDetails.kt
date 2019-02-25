package com.kyj.kotlinjpa.security

import com.kyj.kotlinjpa.domains.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.StringUtils

class CustomUserDetails (
  val user: User,
  val userRoles :MutableList<String>
): UserDetails {
//  lateinit var userRoles: MutableList<String>
//  lateinit var user: User

//  constructor(userRoles: MutableList<String>, user: User): this()

  override fun isEnabled() = user.isEnabled

  override fun getUsername() = user.username

  override fun isCredentialsNonExpired() = user.isCredentialNonExpired

  override fun getPassword() = user.password

  override fun isAccountNonExpired() = user.isAccountNonExpired

  override fun isAccountNonLocked() = user.isAccountNonLocked

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    val roles = StringUtils.collectionToCommaDelimitedString(userRoles)
    return AuthorityUtils.commaSeparatedStringToAuthorityList(roles)
  }
}