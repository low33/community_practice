package org.example.post.repository.post_queue;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.entity.post.UserPostQueueEntity;
import org.example.post.repository.jpa.JpaPostRepository;
import org.example.post.repository.jpa.JpaUserPostQueueRepository;
import org.example.user.repository.entity.UserEntity;
import org.example.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserQueueRedisRepository userQueueRedisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        userQueueRedisRepository.publishPostToFollowerList(postEntity, followersIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        userQueueRedisRepository.publishPostListToFollowingUser(postEntities,userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        userQueueRedisRepository.deleteDeletedFeed(userId, targetId);
    }
}
