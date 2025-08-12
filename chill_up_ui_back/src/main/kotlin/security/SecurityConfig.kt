package org.chillApp.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .build()
    }
}