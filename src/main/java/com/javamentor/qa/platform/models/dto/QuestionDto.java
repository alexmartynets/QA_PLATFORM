package com.javamentor.qa.platform.models.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String username;
    private List<String> tag_name;
    private Integer reputationCount;
    private Integer viewCount = 0;
    private Integer countAnswer = 0;
    private Integer countValuable = 0;
    private LocalDateTime persistDateTime;
}