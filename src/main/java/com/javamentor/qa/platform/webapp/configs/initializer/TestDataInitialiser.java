package com.javamentor.qa.platform.webapp.configs.initializer;

import com.javamentor.qa.platform.service.impl.UserServisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class TestDataInitialiser {

    @Autowired
    private UserServisTest userServisTest;

    void init(){
        System.out.println(userServisTest);
    }
}
