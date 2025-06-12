package org.example.post.domain.content;

import org.example.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreatedCommentContent_thenReturnTextContent() {
        // given
        String contentText = "this is a test content";

        // when
        CommentContent content = new CommentContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreatedCommentContent_thenThrowError() {
        // given
        String content = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵", "삵", "습"})
    void givenContentLengthIsOverAndKorean_whenCreatedCommentContent_thenThrowError(String koreanWord) {
        // given
        String content = koreanWord.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }
}