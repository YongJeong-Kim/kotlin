package com.kyj.kotlinwebflux.domains.person

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
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
    "/imageUpload".nest {
      POST("/", personHandler::imageUpload)
      accept(MediaType.MULTIPART_FORM_DATA)
    }
    "/home".nest {
      GET("/", personHandler::home)
    }
  }
}