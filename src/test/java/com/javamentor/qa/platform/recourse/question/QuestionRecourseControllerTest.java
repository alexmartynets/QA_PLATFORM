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

@DataSet(value = {"question.yml", "users.yml", "answer.yml", "tag.yml", "question_has_tag.yml"}, cleanBefore = true)
public class QuestionRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_Count_Of_Questions_In_DB() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value(10));

    }

    @Test
    void test_Size_Param_From_QuestionController_Method_getPaginationQuestion() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=" + 0))
                .andDo(print())
                .andExpect(jsonPath("$.value").isEmpty());

        this.mockMvc.perform(post("/api/question/pagination?page=4&size=" + 3))
                .andDo(print())
                .andExpect(jsonPath("$.value.size()").value(1));
    }

    @Test
    void test_Page_Param_From_QuestionController_Method_getPaginationQuestion() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=" + 1 + "&size=1"))
                .andDo(print())
                .andExpect(jsonPath("$.value").isEmpty());

        this.mockMvc.perform(post("/api/question/pagination?page=" + 1 + "&size=1"))
                .andDo(print())
                .andExpect(jsonPath("$.value.size()").value(1));
    }

    @Test
    void Test_With_Negative_Param() throws Exception {
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        this.mockMvc.perform(post("/api/question/pagination?page=-1&size=1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        this.mockMvc.perform(post("/api/question/pagination?page=-1&size=-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
