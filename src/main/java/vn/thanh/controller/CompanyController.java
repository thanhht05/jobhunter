package vn.thanh.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.thanh.domain.Company;
import vn.thanh.service.CompanyService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        Company newCompany = this.companyService.handleSaveCompany(company);
        return ResponseEntity.ok(newCompany);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompany() {

        List<Company> companies = this.companyService.handleGetAllCompany();
        return ResponseEntity.ok(companies);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {

        this.companyService.handleDeleteCompanyById(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/companies")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company c) {

        Company company = this.companyService.handleUpdateCompany(c);

        return ResponseEntity.ok(company);
    }

}
