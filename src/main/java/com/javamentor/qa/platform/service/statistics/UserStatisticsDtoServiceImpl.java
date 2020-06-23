package com.javamentor.qa.platform.service.statistics;

import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserStatisticsDtoService;
import com.javamentor.qa.platform.service.statistics.pattern.TabDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatisticsDtoServiceImpl implements UserStatisticsDtoService {

    private final TabDecorator tabDecorator;

    @Autowired
    public UserStatisticsDtoServiceImpl(TabDecorator tabDecorator) {
        this.tabDecorator = tabDecorator;
    }

    @Override
    public UserStatisticDto getUserStatistic(UserDto user, String tab, String sort, int page){
        return tabDecorator.getStatistics(user.getId(), "a.viewCount", 1);
    }
}
