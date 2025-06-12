package org.example.user.domain;

import lombok.Getter;

@Getter
public class UserInfo {

    private final String name;
    private final String profileImageUrl;

    public UserInfo(
            String name,
            String profileImageUrl
    ) {

        // 사용자 유효성 검사 절차
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
