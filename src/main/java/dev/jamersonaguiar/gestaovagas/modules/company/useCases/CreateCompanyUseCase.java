package dev.jamersonaguiar.gestaovagas.modules.company.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.CompanyFoundException;
import dev.jamersonaguiar.gestaovagas.modules.company.entities.CompanyEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((company -> {
                    throw new CompanyFoundException();
                }));

        return this.companyRepository.save(companyEntity);
    }
}
