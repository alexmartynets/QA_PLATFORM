package com.javamentor.qa.platform.models.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class TagDto {

    private Long id;

//    private Long questionId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    Integer QuestionTagCount = 0;
}