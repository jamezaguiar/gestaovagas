package dev.jamersonaguiar.gestaovagas.modules.company.useCases;

import dev.jamersonaguiar.gestaovagas.modules.company.entities.JobEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
