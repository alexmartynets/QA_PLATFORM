package com.javamentor.qa.platform.webapp.configs.initializer;

import com.javamentor.qa.platform.service.impl.TestDataEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestEntityInit {

    @Autowired
    private TestDataEntityService testDataEntityService;

    public void init() throws Exception {
        testDataEntityService.createEntity();
    }
}
