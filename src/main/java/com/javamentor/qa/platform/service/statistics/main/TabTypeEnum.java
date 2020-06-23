package com.javamentor.qa.platform.service.statistics.main;

import com.javamentor.qa.platform.service.statistics.abstracts.Tab;
import com.javamentor.qa.platform.service.statistics.impl.ProfileTabImpl;
import com.javamentor.qa.platform.service.statistics.impl.SummaryTabImpl;

public enum TabTypeEnum {
    PROFILE(ProfileTabImpl.class), SUMMARY(SummaryTabImpl.class), ACTIVE(SummaryTabImpl.class);

    Class<? extends Tab> tabClazz;

    TabTypeEnum(Class<? extends Tab> tabClazz) {
        this.tabClazz = tabClazz;
    }

    public static Tab getTabClazz(String tab) throws IllegalAccessException, InstantiationException {
        return TabTypeEnum.valueOf(tab.toUpperCase()).tabClazz.newInstance();
    }
}