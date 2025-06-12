package org.example.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_COMMENT_LIMIT = 100;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("Content text cannot be null or empty");
        }

        if (MAX_COMMENT_LIMIT < contentText.length()) {
            throw new IllegalArgumentException("Content text is too long");
        }
    }
}
