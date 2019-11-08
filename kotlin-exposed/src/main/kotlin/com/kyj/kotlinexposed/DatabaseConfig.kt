package com.kyj.kotlinexposed

import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig(
  @Value("\${spring.datasource.url}")
  val url: String,
  @Value("\${spring.datasource.username}")
  val username: String,
  @Value("\${spring.datasource.password}")
  val password: String,
  @Value("\${spring.datasource.driverClassName}")
  val driverClassName: String
) {
  @Bean
  fun connectDatabase() =
    Database.connect(url, driverClassName, username, password)
  /*@Bean
fun transactionManager(dataSource: DataSource) =
  SpringTransactionManager(dataSource)*/
}