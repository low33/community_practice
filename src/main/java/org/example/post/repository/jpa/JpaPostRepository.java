package org.example.post.repository.jpa;

import org.example.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT p FROM PostEntity p WHERE p.author.id = :authorId")
    List<PostEntity> findAllPostIdsByAuthorId(Long authorId);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.content = :#{#postEntity.getContent()}, "
        + "p.state = :#{#postEntity.getState()},"
        + "p.updDt = now() "
        + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    /*

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.likeCount = :#{#postEntity.likeCount}, " // 갱신이 유실될 가능 성이 있다.
        + "p.updDt = now() "
        + "WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);

     */

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.likeCount = p.likeCount + :likeCount, "
            + "p.updDt = now() "
            + "WHERE p.id = :postId")
    void updateLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount + 1, "
            + "p.updDt = now() "
            + "WHERE p.id = :id")
    void increasePointCount(Long id);
}
