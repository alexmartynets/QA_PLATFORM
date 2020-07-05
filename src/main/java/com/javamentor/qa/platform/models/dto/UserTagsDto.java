package com.javamentor.qa.platform.models.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserTagsDto {

    private Long tagId;

    private String tagName;

    private String tagDescription;

    private Long countOfTag;
}
