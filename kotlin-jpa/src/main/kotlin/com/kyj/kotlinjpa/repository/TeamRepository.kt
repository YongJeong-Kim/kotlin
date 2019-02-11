package com.kyj.kotlinjpa.repository

import com.kyj.kotlinjpa.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository:JpaRepository<Team, Long> {
}