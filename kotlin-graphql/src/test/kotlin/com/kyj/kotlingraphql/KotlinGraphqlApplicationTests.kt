package com.kyj.kotlingraphql

import com.kyj.kotlingraphql.repository.UserRepositoryCustom
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KotlinGraphqlApplicationTests(

) {
  @Autowired
  lateinit var userRepositoryCustom: UserRepositoryCustom
  @Test
  fun contextLoads() {
    userRepositoryCustom.findAll().forEach(::println)
  }

}
