package com.javamentor.qa.platform.recourse.question;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class QuestionRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = {"question.yml", "users.yml", "answer.yml", "tag.yml", "question_has_tag.yml"}, cleanBefore = true)
    void test_Size_Rand_Param_From_QuestionController() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(3));
    }

    @Test
    @DataSet(value = {"question.yml", "users.yml", "answer.yml", "tag.yml", "question_has_tag.yml"}, cleanBefore = true)
    void test_Count_Of_Questions_In_DB() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value(10));
    }

    @Test
    @DataSet(value = {"question.yml", "users.yml", "answer.yml", "tag.yml", "question_has_tag.yml"}, cleanBefore = true)
    void test_Size_Zero_Param_From_QuestionController_Method_getPaginationQuestion() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DataSet(value = {"question.yml", "users.yml", "answer.yml", "tag.yml", "question_has_tag.yml"}, cleanBefore = true)
    void test_With_Negative_Param() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=-1&size=-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
