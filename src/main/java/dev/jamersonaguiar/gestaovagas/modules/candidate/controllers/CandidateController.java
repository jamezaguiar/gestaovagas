package dev.jamersonaguiar.gestaovagas.modules.candidate.controllers;

import dev.jamersonaguiar.gestaovagas.exceptions.UserFoundException;
import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.CandidateEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.useCases.CreateCandidateUseCase;
import dev.jamersonaguiar.gestaovagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok(result);
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> findById(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var result = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(result);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
