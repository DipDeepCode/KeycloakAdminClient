package ru.ddc.keycloakadminclient2.configuration;

import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakConfig {
    private String authServerUrl;
    private String realmAdmin;
    private String clientIdAdmin;
    private String username;
    private String password;

    @Bean
    public Keycloak keycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realmAdmin)
                .clientId(clientIdAdmin)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .build();
    }
}
