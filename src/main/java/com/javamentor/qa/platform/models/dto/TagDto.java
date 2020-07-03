package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
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

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании.")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении.")
    @ApiModelProperty(notes = "Автоматически генерируемый ID тэга. Не указывать при создании, обязательно указывать при изменении.",
            position = 1)
    private Long id;

    @NotNull(groups = {OnUpdate.class, OnCreate.class},
            message = "Не должно принимать null значение при создании или обновлении")
    @ApiModelProperty(notes = "Название тэга. Обязательно указывать при создании и обновлении.",
            position = 2)
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

    @ApiModelProperty(notes = "Количество вопросов по тэгу добавленных за последнюю неделю.",
            position = 6)
    private Integer questionWeekCount;

    @ApiModelProperty(notes = "Количество вопросов по тэгу добавленных за последний месяц.",
            position = 7)
    private Integer questionMonthCount;

    @ApiModelProperty(notes = "Количество вопросов по тэгу добавленных за последний год.",
            position = 8)
    private Integer questionYearCount;

    @ApiModelProperty(notes = "Дата создания тэга.", position = 4)
    private LocalDateTime persistDateTime;


    public TagDto(@Null(groups = OnCreate.class, message = "Должно принимать null значение при создании.") @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении.") Long id, @NotNull(groups = {OnUpdate.class, OnCreate.class},
            message = "Не должно принимать null значение при создании или обновлении") String name, @NotNull(groups = {OnUpdate.class, OnCreate.class},
            message = "Не должно принимать null значение при создании или обновлении.") String description, Long questionTagCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questionCount = questionCount;
    }
}