package com.kyj.kotlinjpa.domains.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kyj.kotlinjpa.domains.Auditable
import com.kyj.kotlinjpa.domains.role.Role
import javax.persistence.*

@Entity
class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  var username: String,
  @JsonIgnore
  var password: String? = null,
  var name: String,
  var email: String,
  var isAccountNonExpired: Boolean,
  var isAccountNonLocked: Boolean,
  var isCredentialNonExpired: Boolean,
  var isEnabled: Boolean,

  @ManyToMany
  @JoinTable(name = "user_role",
    joinColumns = [JoinColumn(name = "user_id")],
    inverseJoinColumns = [JoinColumn(name = "role_id")])
  @JsonIgnore
  var roles: MutableList<Role>? = null
): Auditable<String>()