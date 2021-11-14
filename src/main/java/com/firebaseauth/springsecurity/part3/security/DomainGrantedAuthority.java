package com.firebaseauth.springsecurity.part3.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class DomainGrantedAuthority implements GrantedAuthority {

    private final EntityType entityType;
    private final Long entityId;
    private final Permission permission;

    @Override
    public String getAuthority() {
        return entityType +
                ":" +
                entityId +
                ":" +
                permission;
    }
}