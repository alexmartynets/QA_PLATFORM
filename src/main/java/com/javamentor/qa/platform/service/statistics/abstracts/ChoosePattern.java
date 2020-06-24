package com.javamentor.qa.platform.service.statistics.abstracts;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;

import java.util.Map;

public interface ChoosePattern {
    Boolean findPattern(String type);

    UserStatisticDto result(String typeTabsAndSort, Map<String, Tabs> map);
}
