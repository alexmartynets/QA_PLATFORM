package com.javamentor.qa.platform.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TagDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime persistDateTime;
}