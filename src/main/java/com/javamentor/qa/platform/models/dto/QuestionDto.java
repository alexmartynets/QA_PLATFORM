package com.javamentor.qa.platform.models.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
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

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String username;

    @NotNull
    private List<TagDto> tags = new ArrayList<>();

    @NotNull
    private Integer reputationCount;

    @NotNull
    private Integer viewCount;

    @NotNull
    private Integer countAnswer;

    @NotNull
    private Integer countValuable;

    @NotNull
    private LocalDateTime persistDateTime;

    private Boolean isHelpful;

    private String lastAnswerName;

    private LocalDateTime lastAnswerDate;

}