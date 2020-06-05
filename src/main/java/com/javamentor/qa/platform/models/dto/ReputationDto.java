package com.javamentor.qa.platform.models.dto;


import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ReputationDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значние при обновлении")
    private Long reputationId;

    private Long reputationCount;

    private Long voiceCount;

    private Long id;

    private String fullName;

    private String about;

    private String city;

    private byte[] imageUser;
}
