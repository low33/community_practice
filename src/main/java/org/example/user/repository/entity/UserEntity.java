package org.example.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.domain.PositiveIntegerCounter;
import org.example.common.repository.entity.TimeBaseEntity;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    // lazy loading 이 default
//    @OneToMany
//    private List<PostEntity> posts;
    // User가 작성한 글이 많고, fetch 가 EAGER인 경우 불필요한 java 인스턴스 사용해 잘 사용하지 않는다.
    // 대신 repository를 이용해서 가져온다.
    // 단방향 맵핑 방법을 선호한다.

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.name();
        this.profileImage = user.profileImage();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }
}
