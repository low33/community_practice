package org.example.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.post.application.Interfaces.LikeRepository;
import org.example.post.domain.Post;
import org.example.post.domain.comment.Comment;
import org.example.post.repository.entity.comment.CommentEntity;
import org.example.post.repository.entity.like.LikeEntity;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.jpa.JpaCommentRepository;
import org.example.post.repository.jpa.JpaLikeRepository;
import org.example.post.repository.jpa.JpaPostRepository;
import org.example.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
//        jpaLikeRepository.save(likeEntity); // save 를 사용할 경우 merge가 발생해 불필요한 조회 발생
        entityManager.persist(likeEntity); // 불필요한 조회 없이 바로 저장
        jpaPostRepository.updateLikeCount(post.getId(), +1);
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(post.getId(), -1);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
//        jpaLikeRepository.save(likeEntity);
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(comment.getId(), +1);
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(comment.getId(), -1);
    }
}
