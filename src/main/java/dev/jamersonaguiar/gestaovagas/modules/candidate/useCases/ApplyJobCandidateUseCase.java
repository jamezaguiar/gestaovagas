package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.JobNotFoundException;
import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.CandidateRepository;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID candidateId, UUID jobId) {
        var candidate = candidateRepository.findById(candidateId).orElseThrow(UserNotFoundException::new);
        var job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
    }

}
