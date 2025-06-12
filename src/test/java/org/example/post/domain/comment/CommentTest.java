package org.example.post.domain.comment;

import org.example.post.domain.Post;
import org.example.post.domain.content.CommentContent;
import org.example.post.domain.content.PostContent;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);
    private final Post post = new Post(1L, user, new PostContent("content"));
    private final CommentContent commentContent = new CommentContent("comment content");
    private final Comment comment = new Comment(1L, post, user, commentContent);

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.likeCount());
    }

    @Test
    void givenCommentCreated_whenLikeByCommentOwnerUser_thenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        // given
        comment.like(otherUser);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.likeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe0() {
        // when
        comment.unlike();

        // then
        assertEquals(0, comment.likeCount());
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String newCommentContent = "new comment content";

        // when
        comment.updateComment(user, newCommentContent);

        // then
        assertEquals(newCommentContent, comment.contentText());
    }

    @Test
    void givenCommentCreated_whenUpdateOtherUserContent_thenThrowException() {
        // given
        String newCommentContent = "new comment content";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }
}