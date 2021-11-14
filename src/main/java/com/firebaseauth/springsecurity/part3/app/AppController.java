package com.firebaseauth.springsecurity.part3.app;

import com.firebaseauth.springsecurity.part3.persistance.Company;
import com.firebaseauth.springsecurity.part3.persistance.CompanyJpaRepository;
import com.firebaseauth.springsecurity.part3.persistance.Subsidiary;
import com.firebaseauth.springsecurity.part3.persistance.SubsidiaryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

    private final CompanyJpaRepository companyRepo;
    private final SubsidiaryJpaRepository subsidiaryRepo;

    @GetMapping(path = "/company/{id}")
    @PreAuthorize("hasAuthority('COMPANY:' + #id  + ':READ')")
    public Company getCompany(@PathVariable Long id) {
        return companyRepo.getById(id);
    }

    @GetMapping(path = "/subsidiary/{id}")
    @PostAuthorize("hasAuthority('COMPANY:' + returnObject.getCompany().getId() + ':WRITE')")
    public Subsidiary getSubsidiary(@PathVariable Long id) {
        return subsidiaryRepo.getById(id);
    }
}
