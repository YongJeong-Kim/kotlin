package com.kyj.kotlingraphql.table

import org.jetbrains.exposed.sql.Table

object Users: Table("users") {
  val id = integer("id").autoIncrement().primaryKey()
  val name = varchar("name", 50).nullable()
}