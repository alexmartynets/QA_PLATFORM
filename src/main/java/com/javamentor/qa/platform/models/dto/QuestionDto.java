package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private UserDto userDto;

    @NotNull
    private List<TagDto> tags = new ArrayList<>();

    @NotNull
    private Integer reputationCount;

    private String description;

    @NotNull
    private Integer viewCount;

    @NotNull
    private Integer countAnswer;

    @NotNull
    private Integer countValuable;

    @NotNull
    private LocalDateTime persistDateTime;

    private Boolean isHelpful;

    private Map<String, String> lastAnswerNameAndDate;
}
