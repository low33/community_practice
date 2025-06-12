package org.example.post.repository.post_queue;

import org.example.post.repository.entity.post.PostEntity;

import java.util.List;

public interface UserQueueRedisRepository {
    void publishPostToFollowerList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId);
    void deleteDeletedFeed(Long userId, Long targetUserId);

}
