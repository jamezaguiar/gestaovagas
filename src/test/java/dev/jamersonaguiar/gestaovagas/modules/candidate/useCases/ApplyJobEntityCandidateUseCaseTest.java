package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.JobNotFoundException;
import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.entities.CandidateEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.ApplyJobRepository;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.CandidateRepository;
import dev.jamersonaguiar.gestaovagas.modules.company.entities.JobEntity;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobEntityCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply to a job with an non-existent candidate")
    public void shouldNotBeAbleToApplyToAJobWithAnNonExistentCandidate() {
        assertThrows(UserNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(null, null);
        });
    }

    @Test
    @DisplayName("Should not be able to apply to a job if the job does not exists")
    public void shouldNotBeAbleToApplyToAJobIfTheJobDoesNotExists() {
        var candidateId = UUID.randomUUID();
        var candidate = CandidateEntity.builder().id(candidateId).build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));


        assertThrows(JobNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(candidateId, null);
        });
    }

    @Test
    @DisplayName("Should be able to apply to a job")
    public void shouldBeAbleToApplyToAJob() {
        var candidateId = UUID.randomUUID();
        var candidate = CandidateEntity.builder().id(candidateId).build();

        var jobId = UUID.randomUUID();
        var job = JobEntity.builder().id(jobId).build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        when(applyJobRepository.save(applyJob)).thenReturn(applyJob);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertNotNull(result);
        assertEquals(candidateId, result.getCandidateId());
        assertEquals(jobId, result.getJobId());
    }
}
