package com.sparta.project.repository.user;

import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {
    Page<User> findUserWith(Pageable pageable, String username, Role role, Boolean isDeleted);
}
