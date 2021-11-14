package com.firebaseauth.springsecurity.part3.admin;

import com.firebaseauth.springsecurity.part3.security.EntityType;
import com.firebaseauth.springsecurity.part3.security.Permission;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final FirebaseAuth firebaseAuth;

    public void setTokenClaims(String uid, Map<EntityType, Map<Long, Set<Permission>>> requestedPermissions) throws FirebaseAuthException {
        var claims = toUserClaims(requestedPermissions);

        firebaseAuth.setCustomUserClaims(uid, claims);
    }

    private Map<String, Object> toUserClaims(Map<EntityType, Map<Long, Set<Permission>>> requestedPermissions) {
        var customClaims = new ArrayList<String>();

        requestedPermissions.forEach((entityType, entityTypePermissions) ->
                entityTypePermissions.forEach((entityId, permissions) ->
                        permissions.forEach(permission ->
                                customClaims.add(generateClaim(entityType, entityId, permission))
                        )));

        return Map.of("custom_claims", customClaims);
    }

    private String generateClaim(EntityType entityType, Long entityId, Permission permission) {
        return entityType +
                ":" +
                entityId +
                ":" +
                permission;
    }
}