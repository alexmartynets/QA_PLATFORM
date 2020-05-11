package com.javamentor.qa.platform.models.dto;

import java.sql.Blob;

import com.javamentor.qa.platform.models.entity.question.Question;
import lombok.*;
import java.time.LocalDateTime;


@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class AnswerDto {
    private Long id;
    private String htmlBody;
    private Integer countValuable;
    private Boolean isHelpful;
    private String fullName;
    private byte[] imageUser;
    private Integer reputationCount;
    private QuestionDto questionDto;
    private UserDto userDto;

    public AnswerDto() {
    }



}
