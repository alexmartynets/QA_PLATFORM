package com.javamentor.qa.platform.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserBadgesDto {
    private Long id;
    private String badges;
    private String description;
}
