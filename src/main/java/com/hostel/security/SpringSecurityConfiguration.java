package com.hostel.security;

import com.hostel.security.filter.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(new AntPathRequestMatcher("/security/registration", "POST")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/security", "POST")).permitAll()

                                .requestMatchers(new AntPathRequestMatcher("/user", "GET")).hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(new AntPathRequestMatcher("/user", "POST")).hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(new AntPathRequestMatcher("/user", "PUT")).hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(new AntPathRequestMatcher("/user/first-name/{id}", "PUT")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/last-name/{id}", "PUT")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/email/{id}", "PUT")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/password/{id}", "PUT")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/{id}", "GET")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/{id}", "DELETE")).hasAnyRole("ADMIN", "MODERATOR")

                                .requestMatchers(new AntPathRequestMatcher("/room", "GET")).hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(new AntPathRequestMatcher("/room/{id}", "GET")).hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(new AntPathRequestMatcher("/room", "PUT")).hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(new AntPathRequestMatcher("/room/{id}", "DELETE")).hasAnyRole("ADMIN", "MODERATOR")

                                .requestMatchers(new AntPathRequestMatcher("/booking", "GET")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/booking/user/{id}", "GET")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/booking/{id}", "GET")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/booking", "POST")).hasAnyRole("USER","ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/booking/{id}", "DELETE")).hasAnyRole("USER","ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/booking", "PUT")).hasRole("ADMIN")

                                .requestMatchers(new AntPathRequestMatcher("/payment", "GET")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/payment/{id}", "GET")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/payment", "POST")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/payment", "PUT")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/payment/{id}", "DELETE")).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}