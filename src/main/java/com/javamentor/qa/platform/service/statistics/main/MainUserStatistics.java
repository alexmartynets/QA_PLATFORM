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

    public UserStatisticDto getResult(String typeTabsAndSort, Integer page) {
        String[] str = typeTabsAndSort.toLowerCase().split(":");
        if (map.containsKey(str[0])) {
            return map.get(str[0]).getList(str[1], Long.parseLong(str[2]), page);
        }
        UserStatisticDto userStatisticDto = UserStatisticDto.builder()
                .answerList(map.get("answer").getList(str[1], Long.parseLong(str[2]), 1).getAnswerList())
                .questionDtoList(map.get("question").getList(str[1], Long.parseLong(str[2]), 1).getQuestionDtoList())
                .userBadges(map.get("badges").getList(str[1], Long.parseLong(str[2]), 1).getUserBadges())
                .userFavoriteQuestions(map.get("bookmarks").getList(str[1], Long.parseLong(str[2]), 1).getUserFavoriteQuestions())
                .userReputation(map.get("reputation").getList(str[1], Long.parseLong(str[2]), 1).getUserReputation())
                .tagDtoList(map.get("tags").getList(str[1], Long.parseLong(str[2]), 1).getTagDtoList())
                .build();
        return userStatisticDto;
    }
}
