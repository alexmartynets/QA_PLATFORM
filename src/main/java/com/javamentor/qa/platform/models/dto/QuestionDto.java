package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class QuestionDto {

    @Null(groups = OnCreate.class, message = "Поле Id должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Поле Id не должно принимать null значение при обновлении")
    @ApiModelProperty(notes = "Автоматически генерируемы ID вопроса. При создании не указывать, указывать при изменении")
    private Long id;

    @NotNull(groups = {OnUpdate.class, OnCreate.class}, message = "Поле title не должно быть пустым")
    @Size(groups = {OnUpdate.class, OnCreate.class}, min = 10)
    @ApiModelProperty(notes = "Заголовок вопроса, поле не должно быть пустым", required = true, example = "Настройка Security Spring")
    @NotBlank
    private String title;

    @Null(groups = OnUpdate.class)
    @NotNull(groups = OnCreate.class, message = "Поле userDto не должно принимать null значение")
    @ApiModelProperty(notes = "Информация об авторе вопроса")
    private UserDto userDto;

    @NotNull(groups = {OnUpdate.class, OnCreate.class}, message = "Поле description не должно принимать null значение")
    @ApiModelProperty(notes = "Описание вопроса", required = true)
    @Size(groups = {OnUpdate.class, OnCreate.class}, min = 10)
    @NotBlank
    private String description;

    @NotNull(groups = OnCreate.class, message = "Поле tags не должно принимать null значение")
    @ApiModelProperty(notes = "Должны быть проставлены теги к вопросу")
    private List<TagDto> tags = new ArrayList<>();

    @ApiModelProperty(notes = "Количество просмотров вопроса", example = "4")
    private Integer viewCount;

    @ApiModelProperty(notes = "Количество ответов на вопрос", example = "3")
    private Integer countAnswer;

    @ApiModelProperty(notes = "Количество пользователей, которые посчитали вопрос полезным", example = "10")
    private Integer countValuable;

    @ApiModelProperty(notes = "Дата публикования вопроса")
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Дата последней редакции вопроса или добавление ответа на него")
    private LocalDateTime lastUpdateDateTime;

    @ApiModelProperty(notes = "Флаг, который помечает вопрос, о наличии ответа, решившего проблему")
    private Boolean isHelpful;

    @ApiModelProperty(notes = "Имя пользователя, который последним дал ответ на вопрос." +
            " Если ответ ещё не дан, то отображается имя автора вопроса.")
    private String lastAnswerName;

    @ApiModelProperty(notes = "Дата последнего ответа на вопрос. Если вопроса нет - дата публикации вопроса.")
    private LocalDateTime lastAnswerDate;

    @ApiModelProperty(name = "Итоговый результат голосования по вопросу от запросившего пользователя")
    private Integer voteByUser;
}