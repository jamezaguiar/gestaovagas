package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.modules.company.entities.JobEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }

}
