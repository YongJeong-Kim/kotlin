package com.kyj.kotlinjpa.domains.document

import com.kyj.kotlinjpa.domains.Auditable
import com.kyj.kotlinjpa.domains.user.User
import javax.persistence.*

@Entity
class Document(
  @Id
  @Column(length = 36)
  var id: String,

  @Column(length = 50, nullable = false)
  var subject: String,
  var filename: String,
  @Column(length = 5)
  var extension: String,
  var path: String,

  @ManyToOne
  @JoinColumn(name = "user_id")
  var user: User? = null
): Auditable<String>()