package com.kyj.kotlingraphql.table

import org.jetbrains.exposed.sql.Table

object Users: Table("users") {
  val id = integer("id").autoIncrement().primaryKey()
  val name = varchar("name", 50).nullable()
  val age = integer("age")
  val hobbyId = (integer("hobby_id") references id).nullable()
}

object Hobbies: Table("hobby") {
  val id = integer("id").autoIncrement().primaryKey()
  val name = varchar("name", 50)
  val description = varchar("description", 50)
}
