package com.kyj.kotlinjpa.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditingConfiguration {
  @Bean
  fun auditorProvider(): AuditorAware<String> =
    AuditorAware<String> {
      Optional.ofNullable(SecurityContextHolder.getContext().authentication?.name)
    }
}