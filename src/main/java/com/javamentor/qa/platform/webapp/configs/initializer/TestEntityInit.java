package com.javamentor.qa.platform.webapp.configs.initializer;

import com.javamentor.qa.platform.service.impl.TestDataEntityService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Builder
public class TestEntityInit {

    @Autowired
    private final TestDataEntityService testDataEntityService;

    private void init() throws Exception {
        testDataEntityService.createEntity();
    }
}
