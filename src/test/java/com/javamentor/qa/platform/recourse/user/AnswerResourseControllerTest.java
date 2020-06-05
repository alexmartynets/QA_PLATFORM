package com.javamentor.qa.platform.recourse.user;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class AnswerResourseControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = {"role.yml", "users.yml", "answer.yml"}, cleanBefore = true)
    void getAllDtoByQuestionId() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/user/question/1/answer"));

    }
}
