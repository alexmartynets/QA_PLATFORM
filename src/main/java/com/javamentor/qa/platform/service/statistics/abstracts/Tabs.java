package com.javamentor.qa.platform.service.statistics.abstracts;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;

import java.util.List;

public interface Tabs {
    UserStatisticDto getList(String sortType, Long userId);
}
