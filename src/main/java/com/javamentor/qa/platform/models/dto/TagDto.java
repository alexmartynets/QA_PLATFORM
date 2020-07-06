package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
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
public class TagDto {

    @Null(groups = OnCreate.class, message = "Поле Id должно принимать null значение при создании")
    private Long id;

//    private Long questionId;

    @NotNull
    private String name;

    @NotNull(groups = {OnUpdate.class, OnCreate.class},
            message = "Не должно принимать null значение при создании или обновлении.")
    @ApiModelProperty(notes = "Описание тэга. Обязательно указывать при создании и обновлении.",
            position = 3)
    private String description;

    @ApiModelProperty(notes = "Количество вопросов по тэгу.",
            position = 5)
    private Integer questionCount;

    @ApiModelProperty(notes = "Количество вопросов по тэгу добавленных за последние 24 часа.",
            position = 6)
    private Integer questionTodayCount;

    @ApiModelProperty(notes = "Количество вопросов по тэгу добавленных за последний месяц.",
            position = 7)
    private Integer questionMonthCount;

    @ApiModelProperty(notes = "Количество вопросов по тэгу добавленных за последний год.",
            position = 8)
    private Integer questionYearCount;

    @ApiModelProperty(notes = "Дата создания тэга.", position = 4)
    private LocalDateTime persistDateTime;

}