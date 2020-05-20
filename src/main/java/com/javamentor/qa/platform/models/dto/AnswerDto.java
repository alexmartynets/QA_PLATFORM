package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.*;

import java.sql.Blob;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class AnswerDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    private Long id;

    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private Long questionId;

    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private String htmlBody;

    private LocalDateTime persistDateTime;
    private LocalDateTime dateAcceptTime;

    private Integer countValuable;

    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    @AssertFalse(groups = OnCreate.class, message = "Должно принимать false значение при создании")
    private Boolean isHelpful;

    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private UserDto userDto;
}