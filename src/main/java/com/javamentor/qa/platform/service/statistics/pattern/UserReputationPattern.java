package com.javamentor.qa.platform.service.statistics.pattern;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.ChoosePattern;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserReputationPattern implements ChoosePattern {
    @Override
    public Boolean findPattern(String type) {
        return type.startsWith("reputation");
    }

    @Override
    public UserStatisticDto result(String typeTabsAndSort, Map<String, Tabs> map) {
        String[] strings = typeTabsAndSort.split(":");
        return map.get(strings[0]).getList(strings[1], Long.parseLong(strings[2]));
    }
}
