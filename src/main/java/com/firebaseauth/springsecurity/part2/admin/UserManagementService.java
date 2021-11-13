package com.firebaseauth.springsecurity.part2.admin;

import com.firebaseauth.springsecurity.part2.security.Permission;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final FirebaseAuth firebaseAuth;

    public void setUserClaims(String uid, List<Permission> requestedPermissions) throws FirebaseAuthException {
        Map<String, Object> claims = Map.of("custom_claims", requestedPermissions);

        firebaseAuth.setCustomUserClaims(uid, claims);
    }
}