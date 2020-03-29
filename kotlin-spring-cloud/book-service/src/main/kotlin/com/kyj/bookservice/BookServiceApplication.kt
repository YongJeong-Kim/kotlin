package com.kyj.bookservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class BookServiceApplication

fun main(args: Array<String>) {
  runApplication<BookServiceApplication>(*args)
}
