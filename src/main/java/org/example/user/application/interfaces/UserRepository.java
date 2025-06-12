package org.example.user.application.interfaces;

import org.example.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
