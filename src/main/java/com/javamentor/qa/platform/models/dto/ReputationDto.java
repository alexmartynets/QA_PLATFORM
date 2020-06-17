package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ReputationDto {

    private Long id;

    private User user;

    private LocalDate persistDate;

    private Integer count;

    private Long allCount;

}
