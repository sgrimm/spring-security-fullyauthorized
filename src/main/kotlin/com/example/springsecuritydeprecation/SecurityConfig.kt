package com.example.springsecuritydeprecation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.AuthenticatedAuthorizationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext

@Configuration
@EnableWebSecurity
class SecurityConfig {
  @Bean
  fun securityFilter(http: HttpSecurity): SecurityFilterChain {
    http {
      // In Spring Security 6.3.4, this works without having to define fullyAuthenticated:
      // authorizeRequests {
      //   authorize("/api/**", fullyAuthenticated)
      // }

      authorizeHttpRequests {
        // Workaround for the missing property
        val fullyAuthenticated =
          AuthenticatedAuthorizationManager.fullyAuthenticated<RequestAuthorizationContext>()

        authorize("/api/**", fullyAuthenticated)
      }
    }

    return http.build()
  }
}
