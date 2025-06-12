package org.example.post.application;

import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savedPost = postService.createPost(postRequestDto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatePost() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(
                savedPost.getId(),
                user.getId(),
                "update", PostPublicationState.PUBLIC
        );
        Post updatedPost = postService.updatePost(savedPost.getId(), updatePostRequestDto);

        // then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLik() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.likeCount());
    }

    @Test
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithLik() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.likeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnliked_thenReturnPostWithUnlik() {
        // given
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.likeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike() {
        // given
        Post savePost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savePost.likeCount());
    }
}