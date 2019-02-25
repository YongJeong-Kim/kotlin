package com.kyj.kotlinjpa.repository

import com.kyj.kotlinjpa.entity.Person
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PersonRepository: JpaRepository<Person, Long> {
  @Query("select p from Person p where p.name = :name")
  fun findPerson(@Param("name") name: String, pageable: Pageable): List<Person>

  @Query("select p from Person p join fetch p.team where p.team.id = 3")
  fun findOneWithTeamById(): List<Person>

  fun findByTeam_Id(id: Long): List<Person>
}