package com.binary.hospital.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private EnrolementDetailService enrolementDetailService;

    @Autowired
    private  JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.

        authorizeHttpRequests(authorize ->
        authorize
                .requestMatchers("/api/v1/patient/create","/api/v1/patient/update/{id}","/api/v1/patient/delete/{id}","/api/v1/patient/all", "/api/v1/doctor/create","/api/v1/doctor/delete/{id}","/api/v1/doctor/update/{id}","/api/v1/doctor/all", "/api/v1/enrolement/register", "/api/v1/enrolement/login").permitAll()
                .anyRequest().authenticated())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
      return http.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(enrolementDetailService)
              .passwordEncoder(noOpPasswordEncoder())
              .and().build();
    }


    @Bean
    public PasswordEncoder noOpPasswordEncoder(){
         return NoOpPasswordEncoder.getInstance();
    }

}
