package org.example.user.application;

import org.example.common.fake.FakeObjectFactory;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.example.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;
    private FollowUserRequestDto requestDTO;

    @BeforeEach
    void setUp() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        user1 = userService.createUser(dto);
        user2 = userService.createUser(dto);
        requestDTO = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(requestDTO);

        // then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenThrowError() {
        // given
        userRelationService.follow(requestDTO);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDTO));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUserFollow_whenFollow_thenUserUnfollowSaved() {
        // given
        userRelationService.follow(requestDTO);

        // when
        userRelationService.unfollow(requestDTO);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDTO));
    }

    @Test
    void givenCreateOneUser_whenUnfollowSelf_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }
}