package org.example.post.repository.post_queue;

import org.example.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {
    /**
     * 새로운 게시물을 작성했을 때, 해당 사용자를 팔로우하는 모든 사용자의 큐에 게시물을 추가합니다.
     */
    void publishPost(PostEntity postEntity);

    /**
     * 사용자가 다른 사용자를 팔로우할 때, 팔로우한 사용자의 게시물을 큐에 추가합니다.
     */
    void saveFollowPost(Long userId, Long targetId);

    /**
     * 사용자가 다른 사용자를 언팔로우할 때, 해당 사용자의 게시물을 큐에서 제거합니다.
     */
    void deleteUnfollowPost(Long userId, Long targetId);
}

/*
    이 interface 는 service layer 에 노출이 되면 안되는 layer 여서 레포지토리의 하위에 넣어줬다.
 */