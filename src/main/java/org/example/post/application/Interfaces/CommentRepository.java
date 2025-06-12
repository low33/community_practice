package org.example.post.application.Interfaces;

import org.example.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);
}
