package com.sparta.project.repository.user;

import com.sparta.project.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
