package dev.jamersonaguiar.gestaovagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Username must not contain whitespace.")
    private String username;

    @Email(message = "Email should be valid.")
    private String email;

    @Length(min = 10, max = 100, message = "Password must be between 10 and 100 characters long.")
    private String password;

    @URL(message = "URL should be valid.")
    private String website;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
