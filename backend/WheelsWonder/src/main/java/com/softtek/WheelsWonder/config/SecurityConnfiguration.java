package com.softtek.WheelsWonder.config;

import com.softtek.WheelsWonder.servicios.IUsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConnfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final IUsuarioServicio userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**").permitAll() // Permitir acceso sin autenticación a los endpoints de autenticación
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll() // Permitir acceso sin autenticación a los métodos GET de CRUD
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll() // Requerir autenticación para los métodos POST de CRUD
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").permitAll() // Requerir autenticación para los métodos PUT de CRUD
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll() // Requerir autenticación para los métodos DELETE de CRUD
                        .requestMatchers(HttpMethod.GET, "/vehiculos/**").permitAll() // Permitir acceso sin autenticación a los métodos GET de CRUD
                        .requestMatchers(HttpMethod.POST, "/vehiculos/**").permitAll() // Requerir autenticación para los métodos POST de CRUD
                        .requestMatchers(HttpMethod.PUT, "/vehiculos/**").permitAll() // Requerir autenticación para los métodos PUT de CRUD
                        .requestMatchers(HttpMethod.DELETE, "/vehiculos/**").permitAll() // Requerir autenticación para los métodos DELETE de CRUD
                        .anyRequest().permitAll()) // Requerir autenticación para cualquier otro endpoint
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}

