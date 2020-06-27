package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    @ApiModelProperty(notes = "Автоматически генерируемый ID комментария. Не указывать при создании, " +
            "обязательно указывать при изменении учетной записи")
    @Null(groups = OnCreate.class, message = "'id' Must be null when creating")
    @NotNull(groups = OnUpdate.class, message = "'id' Must not accept null values when updating")
    private Long id;

    @ApiModelProperty(notes = "Текст комментария. Обязательно указывать при создании",
            required = true, example = "Настройка Security Spring")
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "'text' Must not be null when creating and updating")
    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "'text' Must not consist of spaces")
    @Size(groups = {OnUpdate.class, OnCreate.class}, min = 10, message = "'text' Must be greater than 10 characters")
    private String text;

    @ApiModelProperty(notes = "Тип комментария, показавыет связь комментария с вопросом или ответом",
            required = true, example = "QUESTION или ANSWER")
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "'type' Must not be null when creating and updating")
    private CommentType commentType;

    @ApiModelProperty(notes = "Дата публикования комментария, назначается автоматически при создании")
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Дата последней редакции комментария, назначается автоматически при собновлении")
    private LocalDateTime lastUpdateDateTime;

    @ApiModelProperty(notes = "ID автора комментарий, обязательно указывать при создании и обновлении комментария", required = true)
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "author 'id' Must not be null when creating and updating")
    private Long userId;

    @ApiModelProperty(notes = "Имя автора комментарий")
    private String fullName;
}

