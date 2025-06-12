package org.example.user.application.dto;

import org.example.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl, Integer followingCount, Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.name(), user.profileImage(), user.followingCount(), user.followerCount());
    }
}
