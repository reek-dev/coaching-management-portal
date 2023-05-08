package com.springboot.cmp.config;

import com.springboot.cmp.security.JwtAuthEntryPoint;
import com.springboot.cmp.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    // password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    // create JwtAuthFilter bean
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        final String[] PUBLIC_URLS = {
                "/api/teachers/all"
        };


        // admin-only APIs
        final String[] ADMIN_URLS = {
                "/api/teachers/**"
        };

        // disable CORS and CSRF
        http.cors().and().csrf().disable();

        // add exception handler
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        // change session management to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // authorize HTTP requests
        http.authorizeHttpRequests()
                .requestMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated();

        // add JWT authentication filter
        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails reek = User.builder()
                .username("reek")
                .password(passwordEncoder().encode("reek"))
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("root"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(reek, admin);
    }
}
