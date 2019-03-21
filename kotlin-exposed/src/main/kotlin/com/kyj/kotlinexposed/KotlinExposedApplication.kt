package com.kyj.kotlinexposed

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
class KotlinExposedApplication {
  @Bean
  fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)

  @Bean
  fun connectDB(dataSource: DataSource) = Database.connect(dataSource)
}

fun main(args: Array<String>) {
  runApplication<KotlinExposedApplication>(*args)
  start()
}

fun start() {
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
object Cities: Table() {
  val id = integer("id").autoIncrement().primaryKey() // Column<Int>
  val name = varchar("name", 50) // Column<String>
}
object Users: Table() {
  val id = integer("id").autoIncrement().primaryKey()
  val name = varchar("name", 50)
  val cityId = (integer("city_id") references Cities.id).nullable()
}