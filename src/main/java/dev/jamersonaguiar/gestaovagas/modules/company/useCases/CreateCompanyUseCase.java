package dev.jamersonaguiar.gestaovagas.modules.company.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.CompanyFoundException;
import dev.jamersonaguiar.gestaovagas.modules.company.entities.CompanyEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((company -> {
                    throw new CompanyFoundException();
                }));

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
