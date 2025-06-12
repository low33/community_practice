package org.example.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.domain.PositiveIntegerCounter;
import org.example.common.repository.entity.TimeBaseEntity;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;
import org.example.user.repository.entity.UserEntity;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // EAGER 이 Default
    @ManyToOne()
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) // db에 생성 시 외래키 생성 제한
    private UserEntity author;

    private String content;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    private Integer likeCount;

    // db에서 관리되는 값이므로 mapper class가 아닌 primitive type으로 작성
    @ColumnDefault("0")
    private int commentCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.contentText();
        this.state = post.getState();
        this.likeCount = post.likeCount();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
