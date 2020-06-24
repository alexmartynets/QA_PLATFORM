package com.javamentor.qa.platform.service.statistics.main;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.ChoosePattern;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetUserStatistics {

    private final List<ChoosePattern> choosePatterns;
    private final Map<String, Tabs> map;

    @Autowired
    public GetUserStatistics(List<ChoosePattern> sortingPattern, Map<String, Tabs> map) {
        this.choosePatterns = sortingPattern;
        this.map = map;
    }

    public UserStatisticDto getResult(String typeTabsAndSort) {
        for (ChoosePattern pattern : choosePatterns) {
            if (pattern.findPattern(typeTabsAndSort)) {
                return pattern.result(typeTabsAndSort, map);
            }
        }

        String [] strings = typeTabsAndSort.split(":");
        UserStatisticDto userStatisticDto = UserStatisticDto.builder()
                .answerList(map.get("answer").getList(strings[1], Long.parseLong(strings[2])).getAnswerList())
                .questionDtoList(map.get("question").getList(strings[1], Long.parseLong(strings[2])).getQuestionDtoList())
                .userBadges(map.get("badges").getList(strings[1], Long.parseLong(strings[2])).getUserBadges())
                .userFavoriteQuestions(map.get("bookmarks").getList(strings[1], Long.parseLong(strings[2])).getUserFavoriteQuestions())
                .userReputation(map.get("reputation").getList(strings[1], Long.parseLong(strings[2])).getUserReputation())
                .build();
        return userStatisticDto;
    }
}
