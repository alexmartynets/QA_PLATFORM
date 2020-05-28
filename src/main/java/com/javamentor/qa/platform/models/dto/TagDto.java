package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class TagDto {

    @NotNull(OnCreate.class)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Integer questionCount;
    private Integer questionTodayCount;
    private Integer questionMonthCount;
    private Integer questionYearCount;

    private LocalDateTime persistDateTime;

}