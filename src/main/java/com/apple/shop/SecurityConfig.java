package com.apple.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf((csrf) -> csrf.disable());
    http.authorizeHttpRequests((authorize) ->
        authorize.requestMatchers("/**").permitAll()
    );
    http.formLogin((formLogin) -> formLogin.loginPage("/login")
        .defaultSuccessUrl("/")
        //아래 설정을 안하면 th:if param으로 만들어진 div 가 생성 (현재 url에서)
//        .failureUrl("/fail")
    );
    http.logout(logout -> logout.logoutUrl("/logout"));
    
    return http.build();
  }
  
  
}