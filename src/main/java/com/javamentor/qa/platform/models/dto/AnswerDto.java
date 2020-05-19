package com.javamentor.qa.platform.models.dto;

import lombok.*;

import java.sql.Blob;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {
    private Long id;
    private Long questionId;
    private String htmlBody;
    private LocalDateTime persistDateTime;
    private Integer countValuable;
    private Boolean isHelpful;
    private UserDto userDto;
}
