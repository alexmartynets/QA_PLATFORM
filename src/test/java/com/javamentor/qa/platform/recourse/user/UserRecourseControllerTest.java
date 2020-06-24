package com.javamentor.qa.platform.recourse.user;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class UserRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = {"searchDatasets/roleSearch.yml", "searchDatasets/usersSearch.yml"}, cleanBefore = true)
    void getAllDto()  {
    }
}
