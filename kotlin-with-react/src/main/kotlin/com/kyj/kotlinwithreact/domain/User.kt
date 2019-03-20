package com.kyj.kotlinwithreact.domain

import com.kyj.kotlinwithreact.security.model.Role
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
data class User(
  private var username: String,
  private var password: String,
  var roles: List<Role>? = listOf(Role.ROLE_USER),
  var avatar: String?,
  var enabled: Boolean = true
): UserDetails {
  override fun isEnabled(): Boolean {
    return enabled
  }

  override fun getUsername(): String {
    return username
  }

//  @JsonIgnore
  override fun getPassword(): String {
    return password
  }

  override fun isCredentialsNonExpired(): Boolean {
    return true
  }

  override fun isAccountNonExpired(): Boolean {
    return true
  }

  override fun isAccountNonLocked(): Boolean {
    return true
  }

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    return this.roles!!.map {
      SimpleGrantedAuthority(it.name)
    }.toMutableList()
  }
}