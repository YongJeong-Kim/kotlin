package com.kyj.kotlinwebflux.domains.person

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class PersonRouter(val personHandler: PersonHandler) {
  @Bean
  fun personRoutes() = router {
    "/person".nest {
      GET("/{id}", personHandler::findById)
      POST("/", personHandler::save)
      DELETE("/{id}", personHandler::deleteById)
    }
    "/persons".nest {
      GET("/", personHandler::findAll)
    }
  }
}