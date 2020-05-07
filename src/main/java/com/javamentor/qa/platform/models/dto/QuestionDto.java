package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.question.Tag;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String username;
    private List<TagDto> tagName;
    private Integer reputationCount;
    private Integer viewCount = 0;
    private Integer countAnswer = 0;
    private Integer countValuable = 0;
    private LocalDateTime persistDateTime;
}