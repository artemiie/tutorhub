package com.tutorhub.security;

import com.tutorhub.security.jwt.JwtTokenFilter;
import com.tutorhub.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @SneakyThrows
  public AuthenticationManager authenticationManager(
      final AuthenticationConfiguration authenticationConfiguration) {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  @SneakyThrows
  public SecurityFilterChain configure(final HttpSecurity http) {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(
            configurer ->
                configurer
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(
            configurer ->
                configurer
                    .authenticationEntryPoint(
                        (request, response, exception) -> {
                          response.setStatus(HttpStatus.UNAUTHORIZED.value());
                          response.getWriter().write("Unauthorized");
                        })
                    .accessDeniedHandler(
                        (request, response, exception) -> {
                          response.setStatus(HttpStatus.FORBIDDEN.value());
                          response.getWriter().write("Forbidden");
                        }))
        .authorizeHttpRequests(
            configurer ->
                configurer
                    .requestMatchers("/api/v1/**").authenticated()
                    .requestMatchers("/api/v1/auth").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
        )
        .addFilterBefore(
            new JwtTokenFilter(jwtService, userDetailsService),
            UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
