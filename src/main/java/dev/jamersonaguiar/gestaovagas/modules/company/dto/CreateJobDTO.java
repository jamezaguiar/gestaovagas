package dev.jamersonaguiar.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateJobDTO {

    @Schema(example = "Vaga para dev júnior Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "GymPass, Vale Alimentação, Plano de Saúde.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
