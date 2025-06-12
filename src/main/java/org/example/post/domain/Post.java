package org.example.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.common.domain.PositiveIntegerCounter;
import org.example.post.domain.content.Content;
import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.domain.User;

@Builder
@AllArgsConstructor
@Getter
public class Post {

    private final Long id;
    // user 객체 들고 있기 vs user id 들고있기 생각
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(Long id, User author, String content) {
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
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

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException("Cannot update other user's post");
        }

        this.state = state;
        this.content.updateContent(updateContent);
    }

    public String contentText() {
        return this.content.getContentText();
    }

    public int likeCount() {
        return this.likeCount.getCount();
    }
}
