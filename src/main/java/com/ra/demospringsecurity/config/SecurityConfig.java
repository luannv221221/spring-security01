package com.ra.demospringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity.authenticationProvider(authenticationProvider()).
              csrf(AbstractHttpConfigurer::disable).
              authorizeHttpRequests(
              (auth)->auth.requestMatchers("/user/**","/login/**","/403/**").permitAll()
                      .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                      .anyRequest().authenticated()
      ).formLogin(
              login->login.loginPage("/login").
                      loginProcessingUrl("/login").
                      defaultSuccessUrl("/admin").
                      usernameParameter("userName").
                      passwordParameter("password").failureUrl("/login?err")
                      ).exceptionHandling(exceptionHandling->exceptionHandling.accessDeniedPage("/403"))
              .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
