package com.kyj.authenticationservice

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

@Configuration
class OAuth2Config(
  val authenticationManager: AuthenticationManager,
  @Qualifier("userDetailsServiceBean") val userDetailsService: UserDetailsService
): AuthorizationServerConfigurerAdapter() {
  override fun configure(clients: ClientDetailsServiceConfigurer?) {
    super.configure(clients)
    clients!!.inMemory()
      .withClient("eagleeye")
      .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("thisissecret"))
      .authorizedGrantTypes("refresh_token", "password", "client_credentials")
      .scopes("webclient", "mobileclient")
  }

  override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
    endpoints!!.authenticationManager(authenticationManager)
      .userDetailsService(userDetailsService)
  }

}