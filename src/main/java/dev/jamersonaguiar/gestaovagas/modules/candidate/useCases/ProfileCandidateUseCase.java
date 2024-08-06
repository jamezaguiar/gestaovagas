package dev.jamersonaguiar.gestaovagas.modules.candidate.useCases;

import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import dev.jamersonaguiar.gestaovagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId).orElseThrow(() -> new UserNotFoundException());

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }

}
