package com.kyj.kotlinjpa.domains.role

import com.kyj.kotlinjpa.domains.user.User
import javax.persistence.*

@Entity
class Role(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  var name: String,

  @ManyToMany(mappedBy = "roles")
  var users: MutableList<User>? = null
)