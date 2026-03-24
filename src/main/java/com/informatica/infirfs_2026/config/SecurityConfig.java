package com.informatica.infirfs_2026.config;

import com.informatica.infirfs_2026.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTFilter filter;
    private final UserService userService;

    public SecurityConfig(JWTFilter filter, UserService userService) {
        this.filter = filter;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(userService)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((auth) -> auth
                    // Iedereen mag inloggen en registreren
                    .requestMatchers("/auth/**").permitAll()
                    // Standaard boilerplate code
                    .requestMatchers("/error").anonymous()
                    // Hieronder de endpoints waar iedereen zonder inlog of registratie iets mee mag doen
                    .requestMatchers(HttpMethod.GET, "/product", "/product/**").permitAll()
                    // Hieronder de enpoints waar alleen admins bij mogen
                    .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .build();
    }

    //Encode de passwords zodat niemand deze in kan zien wanneer opgeslagen
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Manier om daadwerklijk bij de user te kunnen komen die geauthenticeerd is
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}



