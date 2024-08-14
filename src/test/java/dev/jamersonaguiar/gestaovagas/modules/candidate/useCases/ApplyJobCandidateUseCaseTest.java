package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.JobNotFoundException;
import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.CandidateEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.CandidateRepository;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

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
        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));


        assertThrows(JobNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(candidateId, null);
        });
    }
}
