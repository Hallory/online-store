package org.electronicsstore.backend.configs;

import jakarta.ws.rs.HttpMethod;
import org.electronicsstore.backend.security.CustomJwtConverter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/categories/**").permitAll()
                                .requestMatchers("/api/auth/public/users/**").permitAll()
                                .requestMatchers("/api/auth/temp/public/**", HttpMethod.GET).permitAll()
                                .requestMatchers("/api/auth/temp/data/**", HttpMethod.GET).permitAll()
                                .requestMatchers("/api/products/**").authenticated()
                                .requestMatchers("/api/auth/**").authenticated()
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new CustomJwtConverter())))
                .build();
    }

    @Bean
    public Keycloak keycloak(
            @Value("${keycloak.backend-client.auth-server-url}") String url,
            @Value("${keycloak.backend-client.realm}") String realm,
            @Value("${keycloak.backend-client.client}") String client,
            @Value("${keycloak.backend-client.credentials.secret}") String secret
    ) {
        return KeycloakBuilder.builder()
                .serverUrl(url)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(client)
                .clientSecret(secret)
                .build();
    }
}
