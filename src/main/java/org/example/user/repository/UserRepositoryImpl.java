package org.example.user.repository;

import lombok.RequiredArgsConstructor;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.repository.entity.UserEntity;
import org.example.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        return entity.toUser();
    }
}
