package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.*;

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
public class TagDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    private Long id;

    @NotNull(groups = {OnUpdate.class, OnCreate.class},
            message = "Не должно принимать null значение при создании или обновлении")
    private String name;

    @NotNull(groups = {OnUpdate.class, OnCreate.class},
            message = "Не должно принимать null значение при создании или обновлении")
    private String description;

    private Integer questionCount;
    private Integer questionTodayCount;
    private Integer questionMonthCount;
    private Integer questionYearCount;

    private LocalDateTime persistDateTime;

}