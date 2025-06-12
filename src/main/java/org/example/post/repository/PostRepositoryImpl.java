package org.example.post.repository;

import lombok.RequiredArgsConstructor;
import org.example.post.application.Interfaces.PostRepository;
import org.example.post.domain.Post;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.jpa.JpaPostRepository;
import org.example.post.repository.post_queue.UserPostQueueCommandRepository;
import org.example.post.repository.post_queue.UserPostQueueCommandRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if (post.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }

        postEntity = jpaPostRepository.save(postEntity);
        userPostQueueCommandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
