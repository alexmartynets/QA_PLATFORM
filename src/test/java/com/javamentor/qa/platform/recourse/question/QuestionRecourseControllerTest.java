package com.javamentor.qa.platform.recourse.question;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class QuestionRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = {"question.yml", "users.yml"}, cleanBefore = true, cleanAfter = true)
    void test_Count_Of_Questions_In_DB() throws Exception {
        this.mockMvc.perform(get("/api/question/pagination?page=1&size=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value(10));
    }

    @Test
    @DataSet(value = {"question.yml", "users.yml", "answer.yml", "tag.yml", "question_has_tag.yml"})
    void test_Size_Rand_Param_From_QuestionController() throws Exception {
        this.mockMvc.perform(get("/api/question/pagination?page=1&size=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(3));
    }

    @Test
    void test_Page_Rand_Param_From_QuestionController() throws Exception {
        this.mockMvc.perform(get("/api/question/pagination?page=7&size=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(1));
    }


    @Test
    void test_Size_Zero_Param_From_QuestionController_Method_getPaginationQuestion() throws Exception {
        this.mockMvc.perform(get("/api/question/pagination?page=0&size=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void test_With_Negative_Param() throws Exception {
        this.mockMvc.perform(get("/api/question/pagination?page=-1&size=-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
