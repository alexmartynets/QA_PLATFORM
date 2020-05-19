package com.javamentor.qa.platform.models.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {
    private Long id;
    @NotNull
    private Long questionId;
    private String htmlBody;
    private LocalDateTime persistDateTime;
    private LocalDateTime dateAcceptTime;
    private Integer countValuable;
    private Boolean isHelpful;
    @NotNull
    private UserDto userDto;
}
