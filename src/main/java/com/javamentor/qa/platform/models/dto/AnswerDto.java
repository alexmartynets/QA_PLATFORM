package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Blob;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    private Long id;

    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private Long questionId;

    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private String htmlBody;

    private LocalDateTime persistDateTime;
    private LocalDateTime dateAcceptTime;
    private Integer countValuable;
    private Boolean isHelpful;

    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private UserDto userDto;
}
