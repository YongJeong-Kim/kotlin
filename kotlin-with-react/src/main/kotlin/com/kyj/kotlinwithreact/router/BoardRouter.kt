package com.kyj.kotlinwithreact.router

import com.kyj.kotlinwithreact.handler.BoardHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class BoardRouter(val boardHandler: BoardHandler) {
  @Bean
  fun boardRoutes() = router {
    "/api".nest {
      "/board".nest {
        "/vacation".nest {
          GET("/", boardHandler::findAllVacation)
        }
      }
    }
  }
}