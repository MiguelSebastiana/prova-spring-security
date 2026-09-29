package br.com.ctw.prova_miguel.config;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

public class SecurityConfig {

      @Bean
      public SecurityFilterChain securityFilterChain (HttpSecurity http){
          return http
                  .csrf(crsf -> crsf.disable())
                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))
                  .authorizeHttpRequests(auth ->
                      auth.requestMatchers(HttpMethod.GET, "/api/v1/auth/login").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/v1/chamados/delete").hasRole("ADMIN")
                          .requestMatchers("**/h2-console").permitAll()
                          .requestMatchers("**/swagger-ui/*").permitAll()
                          .anyRequest().authenticated()
                  )
                  .build();
      }
      @Bean
      public PasswordEncoder passwordEncoder(BCryptPasswordEncoder BCryptPasswordEncoder){
          return BCryptPasswordEncoder;
      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationManager authenticationManager){
          return authenticationManager;
      }
}
