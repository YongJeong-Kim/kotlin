package com.kyj.kotlinjpa.repository

import com.kyj.kotlinjpa.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PersonRepository:JpaRepository<Person, Long> {
  @Query("select p from Person p where p.name = :name")
  fun findPerson(@Param("name") name: String): Person
}