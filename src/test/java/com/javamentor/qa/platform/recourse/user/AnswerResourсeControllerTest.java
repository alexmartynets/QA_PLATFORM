package com.javamentor.qa.platform.recourse.user;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataSet(value = {"role.yml", "users.yml", "answer.yml", "question.yml", "tag.yml", "question_has_tag.yml"}, cleanBefore = true)
public class AnswerResourсeControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAnswerDtoByQuestionID1SortNew() throws Exception {
        mockMvc.perform(get("/api/user/question/1/answer")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].questionId").value(1))
                .andExpect(jsonPath("$.[0].htmlBody").isNotEmpty())
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))
                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))
                .andExpect(jsonPath("$.[1]").exists())
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].questionId").value(1))
                .andExpect(jsonPath("$.[1].htmlBody").value("second answer for Q1"))
                .andExpect(jsonPath("$.[1].persistDateTime").value("2020-05-28T14:00:00"))
                .andExpect(jsonPath("$.[2]").exists())
                .andExpect(jsonPath("$.[2].id").exists())
                .andExpect(jsonPath("$.[2].questionId").value(1))
                .andExpect(jsonPath("$.[2].htmlBody").value("third answer for Q1"))
                .andExpect(jsonPath("$.[2].persistDateTime").value("2020-05-27T13:30:00"))
                .andExpect(jsonPath("$.[2].updateDateTime").value("2020-05-27T13:40:00"))
                .andExpect(jsonPath("$.[3]").doesNotExist());
    }

    @Test
    public void getAllAnswerDtoByQuestionIDSortCount() throws Exception {
        mockMvc.perform(get("/api/user/question/1/answer/sort/count")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].countValuable").value(10))
                .andExpect(jsonPath("$.[1].countValuable").value(9))
                .andExpect(jsonPath("$.[2].countValuable").value(8))
                .andExpect(jsonPath("$.[3]").doesNotExist());
    }

}
