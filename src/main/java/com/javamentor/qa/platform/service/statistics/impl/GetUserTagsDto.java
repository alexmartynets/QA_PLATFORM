package com.javamentor.qa.platform.service.statistics.impl;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tabs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("tags")
public class GetUserTagsDto implements Tabs {

    @Override
    public UserStatisticDto getList(String sortType, Long userId) {
        return null;
    }
}
