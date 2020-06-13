package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModeratorDto {

    private LocalDateTime persistDateTime;

    private Long reputationCount;

    private Long userId;

    private String fullNameUser;

    private String cityUser;

    private byte[] imageUser;
}
