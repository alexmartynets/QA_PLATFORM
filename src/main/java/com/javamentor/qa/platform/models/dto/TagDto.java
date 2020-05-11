package com.javamentor.qa.platform.models.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class TagDto {
    private Long id;
    private String name;
    private String description;
}