package com.example.demo.configs;

import com.example.demo.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    UserService userService;

    public SecurityConfig(UserService userService) {this.userService = userService;}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    http
            .userDetailsService(userService)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/flights/page", true).loginProcessingUrl("/login").permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/login?logout=true").permitAll());
return http.build();
    }
}
