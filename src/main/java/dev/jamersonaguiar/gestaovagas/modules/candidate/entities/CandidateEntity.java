package dev.jamersonaguiar.gestaovagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "John Doe")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Username must not contain whitespace.")
    @Schema(example = "johndoe")
    private String username;

    @Email(message = "Email should be valid.")
    @Schema(example = "johndoe@example.com")
    private String email;

    @Length(min = 10, max = 100, message = "Password must be between 10 and 100 characters long.")
    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "https://my-files/johndoe-resume.pdf")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
