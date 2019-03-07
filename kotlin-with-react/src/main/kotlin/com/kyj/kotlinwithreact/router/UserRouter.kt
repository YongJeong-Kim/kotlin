package com.kyj.kotlinwithreact.router

import com.kyj.kotlinwithreact.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty

@Component
class UserRouter(val userHandler: UserHandler) {
  @Bean
  fun userRoutes() = router {
    "/login".nest {
      POST("/", userHandler::login)
    }
    "/api".nest {
      "/users".nest {
        GET("/", userHandler::findAll)
      }
      "/user".nest {
        GET("/{id}", userHandler::findById)
      }
    }
    "/error/401".nest {
      GET("/", userHandler::unauthorized)
      POST("/", userHandler::unauthorized)
    }
  }
}