package dev.project.backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDTO {
    @NotBlank(message = "{user.email.notBlank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.password.notBlank}")
    @Size(min = 8, max = 12, message = "{user.password.size}")
    private String password;

    @NotBlank(message = "{user.name.notBlank}")
    private String name;
}
