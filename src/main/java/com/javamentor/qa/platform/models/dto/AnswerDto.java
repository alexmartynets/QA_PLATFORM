package com.javamentor.qa.platform.models.dto;

import lombok.*;

import java.sql.Blob;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
