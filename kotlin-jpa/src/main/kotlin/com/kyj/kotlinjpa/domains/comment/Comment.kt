package com.kyj.kotlinjpa.domains.comment

import com.kyj.kotlinjpa.domains.Auditable
import com.kyj.kotlinjpa.domains.board.entity.Board
import javax.persistence.*

@Entity
class Comment(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @Lob
  @Column(nullable = false)
  var text: String,

  @ManyToOne(optional = false)
  @JoinColumn(name = "board_id")
  var board: Board? = null
): Auditable<String>()
