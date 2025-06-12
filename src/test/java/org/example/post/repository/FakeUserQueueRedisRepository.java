package org.example.post.repository;

import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowerList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId : userIdList) {
            if (queue.containsKey(userId)) {
                queue.get(userId).add(postEntity);
            } else {
                queue.put(userId, new HashSet<>(List.of(postEntity)));
            }
        }
    }

    @Override
    public void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).addAll(postEntityList);
        } else {
            queue.put(userId, new HashSet<>(postEntityList));
        }
    }

    @Override
    public void deleteDeletedFeed(Long userId, Long targetUserId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).removeIf(post -> post.getId().equals(targetUserId));
        }
    }

    public List<PostEntity> getPostListByUserId(Long userId) {
        return List.copyOf(queue.get(userId));
    }
}
