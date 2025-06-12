package org.example.common.fake;

import org.example.post.application.CommentService;
import org.example.post.application.Interfaces.CommentRepository;
import org.example.post.application.Interfaces.LikeRepository;
import org.example.post.application.Interfaces.PostRepository;
import org.example.post.application.PostService;
import org.example.post.repository.FakeCommentRepository;
import org.example.post.repository.FakeLikeRepository;
import org.example.post.repository.FakePostRepository;
import org.example.user.application.UserRelationService;
import org.example.user.application.UserService;
import org.example.user.application.interfaces.UserRelationRepository;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.example.user.repository.FakeUserRelationRepository;
import org.example.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static UserRepository fakeUserRepository = new FakeUserRepository();
    private static UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static PostRepository fakePostRepository = new FakePostRepository();
    private static CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}



    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
