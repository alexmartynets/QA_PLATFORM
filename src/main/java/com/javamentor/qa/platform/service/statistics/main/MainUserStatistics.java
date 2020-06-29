package com.javamentor.qa.platform.service.statistics.main;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MainUserStatistics {

    private final Map<String, Tabs> map;

    @Autowired
    public MainUserStatistics(Map<String, Tabs> map) {
        this.map = map;
    }

    public UserStatisticDto getResult(String tab, String sort, Long userId, Integer page) {
        if (map.containsKey(tab)) {
            return map.get(tab).getList(sort, userId, page);
        }
        UserStatisticDto userStatisticDto = UserStatisticDto.builder()
                .answerList(map.get("answer").getList(sort, userId, 1).getAnswerList())
                .questionDtoList(map.get("question").getList(sort, userId, 1).getQuestionDtoList())
                .userBadges(map.get("badges").getList(sort, userId, 1).getUserBadges())
                .userFavoriteQuestions(map.get("bookmarks").getList(sort, userId, 1).getUserFavoriteQuestions())
                .userReputation(map.get("reputation").getList(sort, userId, 1).getUserReputation())
                .tagDtoList(map.get("tags").getList(sort, userId, 1).getTagDtoList())
                .build();
        return userStatisticDto;
    }
}
