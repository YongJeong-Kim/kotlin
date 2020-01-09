package com.kyj.kotlingraphql.config

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
  fun db() = Database.connect(
    url = url,
    user = username,
    driver = driverClassName,
    password = password)
}