package com.kyj.orderservice

import io.specto.hoverfly.junit.core.SimulationSource.dsl
import io.specto.hoverfly.junit.dsl.HoverflyDsl.service
import io.specto.hoverfly.junit.dsl.ResponseCreators.success
import io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith
import io.specto.hoverfly.junit.rule.HoverflyRule
import org.junit.ClassRule
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.concurrent.TimeUnit

@SpringBootTest
class OrderServiceApplicationTests {
  @Autowired
  lateinit var bookService: BookService

  @Autowired
  lateinit var bookClient: BookClient

  @Autowired
  lateinit var template :RestTemplate

  val logger: Logger = LoggerFactory.getLogger(OrderServiceApplicationTests::class.java)

  @ClassRule
  fun hoverflyRule(): HoverflyRule {
    return HoverflyRule.inSimulationMode {
      dsl(
        service("book-service:9100").andDelay(200, TimeUnit.MILLISECONDS).forAll()
          .get(startsWith("/books"))
          .willReturn(success("[{\"id\":\"1\",\"title\":\"aaa\"}]", "application/json")),
        service("book-service:9110").andDelay(50, TimeUnit.MILLISECONDS).forAll()
          .get(startsWith("/books"))
          .willReturn(success("[{\"id\":\"2\",\"title\":\"bbb\"}]", "application/json")),
        service("book-service:9120").andDelay(1000, TimeUnit.MILLISECONDS).forAll()
          .get(startsWith("/books"))
          .willReturn(success("[{\"id\":\"3\",\"title\":\"ccc\"}]", "application/json"))).simulation
    }.printSimulationData()
  }

  @Test
  fun contextLoads() {
    var zone1 = 0
    var zone2 = 0
    var zone3 = 0
    var fallback = 0

    (1..1000).forEach { _ ->
      try {
        //val book = template.getForObject("http://localhost:8090/", String::class.java)
        val books: List<Book> = bookClient.books()
        books.forEach {
          when (it.id) {
            1 -> zone1++
            2 -> zone2++
            3 -> zone3++
            else -> fallback++
          }
        }

        /*val book: Book = bookClient.ping()
        if (book!!.contains("zone1"))
          zone1++
        else if (book.contains("zone2"))
          zone2++
        else zone3++*/
      } catch (e: Exception) {
        fallback++
      }
    }
    logger.info("zone1 count: $zone1, zone2 count: $zone2, zone3 count: $zone3, fallback count: $fallback")
  }

}
