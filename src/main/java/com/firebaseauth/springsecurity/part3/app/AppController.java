package com.firebaseauth.springsecurity.part3.app;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping(path = "/test")
    @PreAuthorize("hasAuthority('READ')")
    public String test(Principal principal) {
        return principal.getName();
    }
}
