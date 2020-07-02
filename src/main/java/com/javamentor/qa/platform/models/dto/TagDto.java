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

    @NotNull
    private String description;

    Integer QuestionTagCount = 0;
}