package com.kyj.kotlinjpa.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Team (
  @get:Id
  @get:GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  var name: String
//  @get:OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
//  @get:JsonIgnore
//  var persons: List<Person> = mutableListOf<Person>()
)