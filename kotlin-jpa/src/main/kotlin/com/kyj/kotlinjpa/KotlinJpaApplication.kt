package com.kyj.kotlinjpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class KotlinJpaApplication

fun main(args: Array<String>) {
  runApplication<KotlinJpaApplication>(*args)
}

