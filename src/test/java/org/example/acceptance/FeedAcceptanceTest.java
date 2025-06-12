package org.example.acceptance;

import org.example.acceptance.utils.AcceptanceTestTemplate;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.domain.content.PostPublicationState;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.example.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User 1 --- follow ---> User 2
     * User 1 --- follow ---> User 3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(
                2L,
                "user 1 can get this post",
                PostPublicationState.PUBLIC
        );
        Long createPostId = requestCreatePost(dto);

        // when, 팔로워 피드 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createPostId, result.get(0).getId());
    }
}

// TODO: 추가 테스트 작성
//- 댓글작성, 리스트 반환 시나리오 테스트 추가
//- 좋아요 눌렀을 때 좋아요 여부 테스트 추가