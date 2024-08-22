package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.JobNotFoundException;
import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.ApplyJobRepository;
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

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        candidateRepository.findById(candidateId).orElseThrow(UserNotFoundException::new);
        jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);


        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        return applyJobRepository.save(applyJob);
    }

}
