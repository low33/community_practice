package org.example.post.application;

import org.example.post.application.Interfaces.CommentRepository;
import org.example.post.application.Interfaces.LikeRepository;
import org.example.post.application.Interfaces.PostRepository;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;
import org.example.post.repository.FakeCommentRepository;
import org.example.post.repository.FakeLikeRepository;
import org.example.post.repository.FakePostRepository;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.repository.FakeUserRepository;

public class PostApplicationTestTemplate {

    final UserRepository userRepository = new FakeUserRepository();
    final PostRepository postRepository = new FakePostRepository();
    final CommentRepository commentRepository = new FakeCommentRepository();
    final LikeRepository likeRepository = new FakeLikeRepository();

    final UserService userService = new UserService(userRepository);
    final PostService postService = new PostService(userService, postRepository, likeRepository);
    final CommentService commentService = new CommentService(userService, postService, commentRepository, likeRepository);

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", null));

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContentTest = "this is test content";
    final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContentTest);
}
