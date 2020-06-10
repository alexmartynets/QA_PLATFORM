package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class QuestionDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @ApiModelProperty(notes = "Автоматически генерируемы ID вопроса. При создании не указывать, указывать при изменении")
    private Long id;


    @NotNull (groups = OnCreate.class, message = "Поле не должно быть пустым")
    @ApiModelProperty(notes = "Заголовок вопроса, поле не должно быть пустым", required = true, example = "Настройка Security Spring")
    private String title;

    @NotNull
    @ApiModelProperty(notes = "Информация об авторе вопроса")
    private UserDto userDto;

    @NotNull
    @ApiModelProperty(notes = "Описание вопроса", required = true)
    private String description;

    @NotNull
    @ApiModelProperty(notes = "Должны быть проставлены тэги к вопросу")
    private List<TagDto> tags = new ArrayList<>();

    @ApiModelProperty(notes = "Количество просмотров вопроса", example = "4")
    private Integer viewCount;

    @ApiModelProperty(notes = "Количество ответов на вопрос", example = "3")
    private Integer countAnswer;

    @ApiModelProperty(notes = "Количество пользователей, которые посчитали вопрос полезным", example = "10")
    private Integer countValuable;

    @ApiModelProperty(notes = "Дата публикования вопроса")
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Флаг, который помечает вопрос, о наличии ответа, решившего проблему")
    private Boolean isHelpful;

    @ApiModelProperty(notes = "Карта для указания имени автора последнего ответа и дату")
    private Map<String, String> lastAnswerNameAndDate;
}