package dev.jamersonaguiar.gestaovagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.jamersonaguiar.gestaovagas.modules.company.dto.AuthCompanyDTO;
import dev.jamersonaguiar.gestaovagas.modules.company.dto.AuthCompanyResponseDTO;
import dev.jamersonaguiar.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret.company}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresAt = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("jamersonaguiar.dev")
                .withExpiresAt(expiresAt)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder().access_token(token).expires_at(expiresAt.toEpochMilli()).build();
    }
}
