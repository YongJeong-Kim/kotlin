package com.kyj.kotlinwithreact.router

import com.kyj.kotlinwithreact.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class UserRouter(val userHandler: UserHandler) {
  @Bean
  fun userRoutes() = router {
    "/login".nest {
      POST("/", userHandler::login)
    }
    "/api".nest {
      "/checkExpiredToken".nest {
        GET("/", userHandler::checkExpiredToken)
      }
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