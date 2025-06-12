package org.example.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {
}
