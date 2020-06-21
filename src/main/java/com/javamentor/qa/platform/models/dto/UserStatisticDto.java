package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.user.Reputation;
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

    private Long totalUserReputation;
    private Long totalUserQuestions;
    private Long totalUserAnswers;
    private Long allViews;
    private UserDto userDto;
    private List<AnswerDto> answerList;
    private List<QuestionDto> questionDtoList;
    private Map<TagDto, Integer> tagDtoList;
    private List<UserBadgesDto> userBadges;
    private List<Reputation> userReputation;
    private List<QuestionDto> userFavoriteQuestions;

}
