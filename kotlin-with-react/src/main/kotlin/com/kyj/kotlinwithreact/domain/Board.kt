package com.kyj.kotlinwithreact.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class Board(
  @Id
  var id: String,
  var username: String,
  var subject: String,
  var content: String,
  var createDate: Date,
  var updateDate: Date,
  var comments: MutableList<String>? = mutableListOf()
) {
//  constructor(username: String, subject: String, content: String, createDate: Date, updateDate: Date, comments: MutableList<String>? = mutableListOf()): super()
//  constructor(id: String, username: String, subject: String, content: String, createDate: Date, updateDate: Date, comments: MutableList<String>? = mutableListOf()): super()
}