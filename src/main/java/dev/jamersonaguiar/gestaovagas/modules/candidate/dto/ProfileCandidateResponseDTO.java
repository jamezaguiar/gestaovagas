package dev.jamersonaguiar.gestaovagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "johndoe")
    private String username;

    @Schema(example = "johndoe@example.com")
    private String email;

    private UUID id;

    @Schema(example = "John Doe")
    private String name;
}
