package dev.jamersonaguiar.gestaovagas.modules.company.controllers;

import dev.jamersonaguiar.gestaovagas.modules.company.dto.CreateJobDTO;
import dev.jamersonaguiar.gestaovagas.modules.company.entities.JobEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public JobEntity create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        
        var jobEntity = JobEntity.builder()
                .companyId(UUID.fromString(companyId.toString()))
                .benefits(jobDTO.getBenefits())
                .description(jobDTO.getDescription())
                .level(jobDTO.getLevel())
                .build();

        return this.createJobUseCase.execute(jobEntity);
    }
}
