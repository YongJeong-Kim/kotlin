package com.kyj.kotlinjpa.domains.board.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kyj.kotlinjpa.domains.Auditable
import com.kyj.kotlinjpa.domains.comment.Comment
import javax.persistence.*

@Entity
class Board(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @Column(length = 50, nullable = false)
  var subject: String,

  @Column(nullable = false)
  @Lob
  var text: String,

  @OneToMany(mappedBy = "board")
  @JsonIgnore
  var comments: MutableList<Comment>? = null
): Auditable<String>()
