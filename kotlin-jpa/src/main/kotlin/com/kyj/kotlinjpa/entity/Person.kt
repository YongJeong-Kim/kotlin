package com.kyj.kotlinjpa.entity

import javax.persistence.*

@Entity
data class Person (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  var name: String,
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
//  @JoinColumn(name = "team_id")
  var team: Team? = null
)

