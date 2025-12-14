package dev.project.backend.user.service;

import dev.project.backend.user.dto.SignupRequestDTO;
import dev.project.backend.user.entity.User;
import dev.project.backend.user.exception.DuplicateEmailException;
import dev.project.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
            request.getEmail(),
            encodedPassword,
            request.getName()
        );

        userRepository.save(user);
    }
}
