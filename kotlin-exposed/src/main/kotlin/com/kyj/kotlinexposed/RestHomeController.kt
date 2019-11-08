package com.kyj.kotlinexposed

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestHomeController {
  @GetMapping("/test")
  fun test() {
    println("in start")
    transaction {
      println("start transaction")
      SchemaUtils.create(Cities, Users)
      val id = Cities.insert {
        it[name] = "St. qqq"
      } get Cities.id
      val user1 = Users.insert {
        it[name] = "user1"
        it[cityId] = id
      } get Users.id
      println("end transaction")
    }
    println("end start")
  }
}

object Cities: Table("CITIES") {
  val id = integer("id").autoIncrement().primaryKey() // Column<Int>
  val name = varchar("name", 50) // Column<String>
}
object Users: Table("USERS") {
  val id = integer("id").autoIncrement().primaryKey()
  val name = varchar("name", 50)
  val cityId = (integer("city_id") references Cities.id).nullable()
}