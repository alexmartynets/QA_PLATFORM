package com.javamentor.qa.platform.service.statistics.pattern;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;
import com.javamentor.qa.platform.service.statistics.abstracts.Tab;
import com.javamentor.qa.platform.service.statistics.main.TabTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class TabDecorator implements Tab {
    protected Tab tab;

    public TabDecorator(String tab) {
        try {
            this.tab = TabTypeEnum.getTabClazz(tab);
        } catch (NullPointerException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
            try {
                this.tab = TabTypeEnum.getTabClazz("PROFILE");
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public UserStatisticDto getStatistics(Long user_id, String sort, int page) {
        return this.tab.getStatistics(user_id, sort, page);
    }

    public String methodName(String sort, int page) {
        return null;
    }
}