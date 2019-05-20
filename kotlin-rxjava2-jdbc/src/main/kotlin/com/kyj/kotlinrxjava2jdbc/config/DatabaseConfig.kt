package com.kyj.kotlinrxjava2jdbc.config

import org.davidmoten.rx.jdbc.ConnectionProvider
import org.davidmoten.rx.jdbc.Database
import org.davidmoten.rx.jdbc.pool.Pools
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.DriverManager


@Configuration
class DatabaseConfig {
  @Value("\${my.datasource.url}")
  lateinit var url: String
  @Value("\${my.datasource.username}")
  lateinit var username: String
  @Value("\${my.datasource.password}")
  lateinit var password: String
  @Value("\${my.datasource.driver-class-name}")
  lateinit var className: String

  @Bean
  fun db(): Database {
    Class.forName(className)
    val connection = DriverManager.getConnection(url, username, password)
    val pool =
      Pools.nonBlocking()
        .maxPoolSize(Runtime.getRuntime().availableProcessors() * 5)
//        .connectionProvider(ConnectionProvider.from(connection))
        .connectionProvider(ConnectionProvider.from(url, username, password))
        .build()
    return Database.from(pool)
  }
}