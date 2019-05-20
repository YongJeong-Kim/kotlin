package com.kyj.kotlinrxjava2jdbc

import io.reactivex.rxkotlin.toObservable
import org.davidmoten.rx.jdbc.ConnectionProvider
import org.davidmoten.rx.jdbc.Database
import org.davidmoten.rx.jdbc.pool.Pools
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.DriverManager
import javax.sql.DataSource

@SpringBootApplication
open class KotlinRxjava2JdbcApplication {
}


fun main(args: Array<String>) {
  runApplication<KotlinRxjava2JdbcApplication>(*args)
}

@RestController
class TestController(
  val userRepository: UserRepository
) {
  @GetMapping("/")
  fun index() = userRepository.findAll()
}