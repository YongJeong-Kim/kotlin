package com.kyj.kotlinwebflux.domains.person

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Person(
  @Id
  var id: Long,
  var name: String,
  var address: Address? = null
) {
  data class Address(
    var phoneNumber: String,
    var zipCode: String
  )
}