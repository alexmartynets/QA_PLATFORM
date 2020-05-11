package com.javamentor.qa.platform.models.dto;

import lombok.*;

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
    private Long id;
    private String title;
    private String username;
    private List<TagDto> tags = new ArrayList<>();
    private Integer reputationCount;
    private Integer viewCount;
    private Integer countAnswer;
    private Integer countValuable;
    private LocalDateTime persistDateTime;
    private Boolean isHelpful;
}