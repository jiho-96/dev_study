package dev.project.backend.user.service;

import dev.project.backend.user.dto.SignupRequestDTO;
import dev.project.backend.user.entity.User;
import dev.project.backend.user.exception.DuplicateEmailException;
import dev.project.backend.user.exception.PasswordNotMatchException;
import dev.project.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void signup(SignupRequestDTO request) {

        if (!request.isPasswordMatched()) {
            throw new PasswordNotMatchException();
        }


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.create(
            request.getEmail(),
            encodedPassword,
            request.getName()
        );

        userRepository.save(user);
    }
}
