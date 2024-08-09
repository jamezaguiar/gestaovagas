package dev.jamersonaguiar.gestaovagas.modules.candidate.controllers;

import dev.jamersonaguiar.gestaovagas.exceptions.UserFoundException;
import dev.jamersonaguiar.gestaovagas.exceptions.UserNotFoundException;
import dev.jamersonaguiar.gestaovagas.modules.candidate.CandidateEntity;
import dev.jamersonaguiar.gestaovagas.modules.candidate.useCases.CreateCandidateUseCase;
import dev.jamersonaguiar.gestaovagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import dev.jamersonaguiar.gestaovagas.modules.candidate.useCases.ProfileCandidateUseCase;
import dev.jamersonaguiar.gestaovagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

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

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato", description = "Informações do candidato")
    @Operation(
            summary = "Listagem de vagas disponíveis para o candidato",
            description = "Esta função é responsável por listar todas as vagas disponíveis, baseada no filtro."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobsByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
