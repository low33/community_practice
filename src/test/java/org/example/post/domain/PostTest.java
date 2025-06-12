package org.example.post.domain;

import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);
    // swift 에서는 이렇게 접근 불가능한데 java 에서는 가능한 이유???
    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeContShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.likeCount());
    }

    @Test
    void givenPostCreated_whenLikeByPostOwnerUser_thenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.likeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0() {
        // when
        post.unlike();

        // then
        assertEquals(0, post.likeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String newPostContent = "new content";

        // when
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        // then
        assertEquals(newPostContent, post.contentText());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUserContent_thenThrowException() {
        // given
        String newPostContent = "new content";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }

    // region homework

    /*

    // 프로퍼티를 어떻게 구성해야지 적절할지
    // 매직 넘버들 관리
    private User author = new User(1L, new UserInfo("tester", ""));
    private PostContent content = new PostContent("test1234");
    private Post post;


    @BeforeEach
    void setUp() {
        author = new User(1L, new UserInfo("tester", ""));
        content = new PostContent("test1234");
        post = new Post(1L, author, content);
    }

    @Test
    void givenNullAuthor_whenCreated_thenThrowError() {
        // given
        User author = null;
        PostContent content = new PostContent("test1234");

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Post(1L, author, content));
    }

    // 네이밍 적절한지
    @Test
    void givenNewPost_whenCreated_thenPublicState() {
        // given
        // when
        // then
        assertEquals(PostPublicationState.PUBLIC, post.getState());
    }

    @Test
    void givenMyPost_whenLikeMyPost_thenThrowError() {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> post.like(author));
    }

    @Test
    void givenPostUser_whenLikePost_thenLikeCountIncrease() {
        // given
        User user = new User(2L, new UserInfo("tester", ""));

        // when
        post.like(user);

        // then
        assertEquals(1, post.getLikeCount().getCount());
    }

// unlike 로직에서 자기 글인지 테스트하는 로직 없는데 괜찮은지?
//    @Test
//    void givenMyPost_whenUnlikeMyPost_thenThrowError() {
//        // given
//        // when
//        // then
//        assertThrows(IllegalArgumentException.class, () -> post.unlike(author));
//    }

    @Test
    void givenOneLikedPostOneUser_whenUnlikePost_thenLikeCountDecrease() {
        // given
        User user = new User(2L, new UserInfo("tester", ""));
        post.like(user);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount().getCount());
    }

     */

    // endregion
}