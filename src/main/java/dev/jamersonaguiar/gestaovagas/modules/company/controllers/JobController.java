package dev.jamersonaguiar.gestaovagas.modules.company.controllers;

import dev.jamersonaguiar.gestaovagas.modules.company.entities.JobEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public JobEntity create(@Valid @RequestBody JobEntity job) {
        return this.createJobUseCase.execute(job);
    }
}
