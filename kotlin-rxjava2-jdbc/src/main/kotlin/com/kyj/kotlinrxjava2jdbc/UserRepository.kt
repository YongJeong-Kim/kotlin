package com.kyj.kotlinrxjava2jdbc

import org.davidmoten.rx.jdbc.Database
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux

@Component
class UserRepository(
  val db: Database
) {
  fun findAll(): Flux<UserModel> {
    db.select("select name from Users").autoMap(User::class.java).subscribe { println(it.name() == "user1") }
    return db.select("select * from Users").get { rs ->
      UserModel().apply {
      id = rs.getInt("id")
      name = rs.getString("name")
      cityId = rs.getInt("city_id")
    } }.toFlux()
  }
}