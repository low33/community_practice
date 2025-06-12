package org.example.post.repository.jpa;

import org.example.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    void deleteByUserIdAndAuthorId(Long userId, Long authorId);
}
