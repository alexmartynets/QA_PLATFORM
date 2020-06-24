package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.user.Reputation;
import io.swagger.annotations.ApiModelProperty;
import javafx.util.Pair;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserStatisticDto {

    @ApiModelProperty(notes = "Рупутация пользователя, за весь период")
    private Long totalUserReputation;

    @ApiModelProperty(notes = "Количество всех вопросов пользователя")
    private Long totalUserQuestions;

    @ApiModelProperty(notes = "Количество всех ответов пользователя")
    private Long totalUserAnswers;

    @ApiModelProperty(notes = "Примерное количество людей которых затронул пользователь " +
            "(сумма просмотра вопросов и ответов пользователя)")
    private Long allViews;

    @ApiModelProperty(notes = "Данные пользователя")
    private UserDto userDto;

    @ApiModelProperty(notes = "Pair ответов пользователя. Long - количество всех ответов. " +
            "List<AnswerDto> - лист ответов, максимальное количество объектов в листе - 20")
    private Pair<Long, List<AnswerDto>> answerList;

    @ApiModelProperty(notes = "Pair вопросов пользователя. Long - количество всех вопросов. " +
            "List<QuestionDto> - лист вопросов, максимальное количество объектов в листе - 20")
    private Pair<Long, List<QuestionDto>> questionDtoList;

    @ApiModelProperty(notes = "Pair тэгов пользователя. Long - количество всех тэгов. " +
            "List<UserTagsDto> - лист тэгов, максимальное количество объектов в листе - 42")
    private Pair<Long, List<UserTagsDto>> tagDtoList;

    @ApiModelProperty(notes = "Pair знаков пользователя. Long - количество всех полученных знаков. " +
            "List<UserBadgesDto> - лист знаков, максимальное количество объектов в листе - 42")
    private Pair<Long, List<UserBadgesDto>> userBadges;

    @ApiModelProperty(notes = "Pair вопросов пользователя отмеченных как Favorite. Long - количество всех вопросов. " +
            "List<QuestionDto> - лист вопросов, максимальное количество объектов в листе - 42")
    private Pair<Long, List<QuestionDto>> userFavoriteQuestions;

    @ApiModelProperty(notes = "List<Reputation> - лист рупутации пользователя по дням.")
    private List<Reputation> userReputation;

}
