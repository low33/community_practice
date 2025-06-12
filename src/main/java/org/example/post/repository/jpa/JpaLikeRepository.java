package org.example.post.repository.jpa;

import org.example.post.repository.entity.like.LikeEntity;
import org.example.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

}
