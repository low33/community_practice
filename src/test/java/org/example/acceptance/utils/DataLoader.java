package org.example.acceptance.utils;

import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.example.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.example.acceptance.steps.UserAcceptanceSteps.followUser;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
