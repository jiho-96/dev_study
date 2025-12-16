package dev.project.backend.user.controller;

import dev.project.backend.user.dto.EmailCheckResponseDTO;
import dev.project.backend.user.dto.SignupRequestDTO;
import dev.project.backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/email-exists")
    public ResponseEntity<EmailCheckResponseDTO> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(
            new EmailCheckResponseDTO(userService.isEmailExists(email))
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequestDTO request) {
        userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
