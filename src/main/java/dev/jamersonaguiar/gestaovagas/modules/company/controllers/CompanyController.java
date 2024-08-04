package dev.jamersonaguiar.gestaovagas.modules.company.controllers;

import dev.jamersonaguiar.gestaovagas.exceptions.CompanyFoundException;
import dev.jamersonaguiar.gestaovagas.modules.company.entities.CompanyEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        try {
            var result = this.createCompanyUseCase.execute(company);
            return ResponseEntity.ok(result);
        } catch (CompanyFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
