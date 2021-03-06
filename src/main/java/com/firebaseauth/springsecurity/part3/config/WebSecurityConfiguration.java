package com.firebaseauth.springsecurity.part3.config;

import com.firebaseauth.springsecurity.part3.security.DomainGrantedAuthority;
import com.firebaseauth.springsecurity.part3.security.EntityType;
import com.firebaseauth.springsecurity.part3.security.Permission;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt ->
                Optional.ofNullable(jwt.getClaimAsStringList("custom_claims"))
                        .stream()
                        .flatMap(Collection::stream)
                        .flatMap(claim -> {
                            var parts = claim.split(":", 3);

                            EntityType entityType;
                            Long entityId;
                            Permission permission;

                            try {
                                entityType = EntityType.valueOf(parts[0]);
                                entityId = Long.valueOf(parts[1]);
                                permission = Permission.valueOf(parts[2]);
                            } catch (IllegalArgumentException e) {
                                return Stream.empty();
                            }

                            return Stream.of(new DomainGrantedAuthority(entityType, entityId, permission));
                        })
                        .collect(Collectors.toList()));

        return converter;
    }
}