package com.kyj.kotlinjpa.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
  val userDetailsService: UserDetailsService
): WebSecurityConfigurerAdapter() {

  @Autowired
  @Throws(Exception::class)
  fun configAuthentication(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  @Bean
  override fun userDetailsService(): UserDetailsService = super.userDetailsService()

  @Throws(Exception::class)
  override fun configure(web: WebSecurity?) {
		web!!.ignoring().antMatchers("/css/**", "/js/**")
  }

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests()
      .antMatchers("/", "/home/**").authenticated().anyRequest().permitAll()
//      .antMatchers("/admin/**").hasAuthority(Role.RoleName.ROLE_ADMIN)
//      .antMatchers("/user/**").hasAnyAuthority(Role.RoleName.ROLE_ADMIN, Role.RoleName.ROLE_USER)
      //				.antMatchers("/css/**", "/js/**").permitAll()
      .and()
      .formLogin()
      .loginPage("/login")
      .usernameParameter("username")
      .passwordParameter("password")
      .defaultSuccessUrl("/home")
      /*			  .successHandler(authSuccessHandler)
              .failureHandler(authFailureHandler)*/
      .and()
      .logout()
      .logoutSuccessUrl("/login?logout")
      .and()
      .exceptionHandling()
      .accessDeniedPage("/error/403")
      .and()
      .exceptionHandling()
//      .authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"))
      //			.and()
      //				.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
      .and()
      .csrf().disable()

//    http.sessionManagement().maximumSessions(1).expiredUrl("/login").sessionRegistry(sessionRegistry());
  }
}