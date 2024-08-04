package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.UserFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.CandidateEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        return this.candidateRepository.save(candidate);
    }
}
