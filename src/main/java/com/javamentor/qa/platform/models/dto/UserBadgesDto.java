package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.Badges;
import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserBadgesDto {

    private Long id;

    private User user;

    private Badges badges;

    private Boolean ready;

    private Integer countOfBadges;
}
