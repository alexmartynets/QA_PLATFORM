package com.javamentor.qa.platform.models.dto;

import lombok.*;
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
    private LocalDateTime persistDateTime;
    private String htmlBody;
    private Integer countValuable;
    private Boolean isHelpful;
    private UserDto userDto ;
}
