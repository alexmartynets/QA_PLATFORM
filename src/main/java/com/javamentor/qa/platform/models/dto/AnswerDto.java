package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    @ApiModelProperty(notes = "Автоматически генерируемый ID ответа. Не указывать при создании, обязательно указывать при изменении",
            position = 1)
    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    private Long id;

    @ApiModelProperty(notes = "ID ответа. Обязательно указывать при создании и при изменении",
            position = 2, required = true)
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private Long questionId;

    @ApiModelProperty(notes = "Текст ответа. Обязательно указывать при создании",
            position = 3, required = true)
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    private String htmlBody;

    @ApiModelProperty(notes = "Дата создания ответа, явно указывать не нужно, назначается автоматически при создании",
            position = 4)
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Дата отметки ответа полезным, явно указывать не нужно, назначается автоматически при изменении isHelpful=true",
            position = 5)
    private LocalDateTime dateAcceptTime;

    @ApiModelProperty(notes = "Дата изменения тектса ответа, явно указывать не нужно, назначается автоматически при изменении htmlBody",
            position = 6)
    private LocalDateTime updateDateTime;

    @ApiModelProperty(notes = "Счетчик полезности ответа, ставится разными пользователями, по умолчанию при создани равен 0",
            position = 7)
    private Integer countValuable;

    @ApiModelProperty(notes = "Отметка полезности ответа (т.е. ответ помог решить вопрос). Ставится только пользователем задавшим вопрос, по умолчанию при создани false",
            position = 8)
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Не должно принимать null значение при создании или обновлении")
    @AssertFalse(groups = OnCreate.class, message = "Должно принимать false значение при создании")
    private Boolean isHelpful;

    @ApiModelProperty(notes = "Маркер отмечающий удален ответ или нет, по умолчанию при создани равен false",
            position = 9)
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Не должно принимать null значение при создании или обновлении")
    @AssertFalse(groups = OnCreate.class, message = "Должно принимать false значение при создании")
    private Boolean isDeleted;

    @ApiModelProperty(notes = "Пользователь давший ответ, обязательно указывать ID", required = true,
            position = 10)
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @NotNull(groups = OnCreate.class, message = "Не должно принимать null значение при создании")
    private UserDto userDto;
}
