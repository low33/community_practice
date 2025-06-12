package org.example.post.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.common.domain.PositiveIntegerCounter;
import org.example.post.domain.content.CommentContent;
import org.example.post.domain.content.Content;
import org.example.post.domain.Post;
import org.example.user.domain.User;

@Builder
@AllArgsConstructor
@Getter
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Post post, User author, String content) {
        return new Comment(null, post, author, new CommentContent(content));
    }

    public Comment(Long id, Post post, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }

        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException("Cannot like my post");
        }

        likeCount.increase();
    }

    public void unlike() {
        this.likeCount.decrease();
    }

    public void updateComment(User author, String updatedContent) {
        if (!this.author.equals(author)) {
            throw new IllegalArgumentException("Cannot update other user's comment");
        }

        this.content.updateContent(updatedContent);
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public int likeCount() {
        return likeCount.getCount();
    }

    public String contentText() {
        return content.getContentText();
    }

    public Content getContentObject() {
        return content;
    }
}
