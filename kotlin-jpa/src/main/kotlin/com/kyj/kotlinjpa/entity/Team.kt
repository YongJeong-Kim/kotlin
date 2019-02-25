package com.kyj.kotlinjpa.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Team (
  @get:Id
  @get:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  var name: String,
  @get:OneToMany(mappedBy = "team")
  @get:JsonIgnore
  var persons: MutableList<Person>
)