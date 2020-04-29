package com.kyj.authenticationservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
class WebSecurityConfigurer : WebSecurityConfigurerAdapter() {
  @Bean
  override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

  @Bean
  override fun userDetailsServiceBean(): UserDetailsService = super.userDetailsServiceBean()

  override fun configure(auth: AuthenticationManagerBuilder?) {
    val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
    auth!!.inMemoryAuthentication()
      .passwordEncoder(encoder)
      .withUser("john.carnell").password(encoder.encode("password1"))
      .roles("USER")
      .and()
      .withUser("william.woodward").password(encoder.encode("password2"))
      .roles("USER", "ADMIN")
  }
}