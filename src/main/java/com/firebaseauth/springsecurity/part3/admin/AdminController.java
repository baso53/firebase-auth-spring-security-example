package com.firebaseauth.springsecurity.part3.admin;

import com.firebaseauth.springsecurity.part3.security.EntityType;
import com.firebaseauth.springsecurity.part3.security.Permission;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserManagementService userManagementService;

    @Secured("ROLE_ANONYMOUS")
    @PostMapping(path = "/user-claims/{uid}")
    public void setUserClaims(
            @PathVariable String uid,
            @RequestBody Map<EntityType, Map<Long, Set<Permission>>> requestedClaims
    ) throws FirebaseAuthException {
        userManagementService.setTokenClaims(uid, requestedClaims);
    }
}
