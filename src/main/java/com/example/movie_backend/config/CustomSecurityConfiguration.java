package com.example.movie_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Objects;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class CustomSecurityConfiguration {
    private final CommonProperties commonProperties;
   

    public CustomSecurityConfiguration(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        corsConfig(http);
        permitAll(http);
       http.oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt
            .jwtAuthenticationConverter(token -> new CustomAuthenticationToken(token)) 
        )
    );

        return http.build();
    }
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(commonProperties.getSecurity().getOauth2().getResourceServer().getJwt().getJwkSetUri()).build();
    }


    private void permitAll(HttpSecurity http) throws Exception {
        if (!Objects.isNull(commonProperties.getPermitAllPathPatterns())) {
            for (String path : commonProperties.getPermitAllPathPatterns()) {
                http.authorizeHttpRequests().antMatchers(path).permitAll();
            }
        }
        http.authorizeHttpRequests().anyRequest().authenticated();
    }

    private void corsConfig(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        if (!Objects.isNull(commonProperties.getCors().getOrigins())) {
            configuration.setAllowedOrigins(commonProperties.getCors().getOrigins());
        }

        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        http.cors(cors -> cors.configurationSource(source));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
