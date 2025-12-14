package dev.project.backend.user.repository;

import dev.project.backend.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 없을 수도 있는 조회 결과
    boolean existsByEmail(String email); // 중복체크
}
