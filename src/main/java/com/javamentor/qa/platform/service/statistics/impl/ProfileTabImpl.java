package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tab;

public class ProfileTabImpl implements Tab {
    @Override
    public UserStatisticDto getStatistics(Long user_id, String sort, int page) {
        return null;
    }
}
