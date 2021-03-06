package com.kyj.kotlinwithreact

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinWithReactApplication

fun main(args: Array<String>) {
  runApplication<KotlinWithReactApplication>(*args)
}