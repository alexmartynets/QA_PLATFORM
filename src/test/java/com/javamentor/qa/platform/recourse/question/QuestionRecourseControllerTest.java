package com.javamentor.qa.platform.recourse.question;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataSet(value = {"question/roleQuestionApi.yml",
        "question/usersQuestionApi.yml",
        "question/answerQuestionApi.yml",
        "question/questionQuestionApi.yml",
        "question/tagQuestionApi.yml",
        "question/question_has_tagQuestionApi.yml"}, cleanBefore = true, cleanAfter = true)
public class QuestionRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getQuestionList() throws Exception {
        this.mockMvc.perform(get("/api/user/question/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("8"));
    }

    @Test
    void getQuestionIsPresent() throws Exception {
        this.mockMvc.perform(get("/api/user/question/1?userId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Question number one"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.reputationCount").value("2"))
                .andExpect(jsonPath("$.description").value("some description for first question"))
                .andExpect(jsonPath("$.tags.[0].id").value("1"))
                .andExpect(jsonPath("$.tags.[0].name").value("java"))
                .andExpect(jsonPath("$.tags.[0].description").value("Java the best language"))
                .andExpect(jsonPath("$.viewCount").value("3"))
                .andExpect(jsonPath("$.countAnswer").value("2"))
//                .andExpect(jsonPath("$.countValuable").value("1"))
                .andExpect(jsonPath("$.persistDateTime").value("2020-01-01T13:58:56"))
                .andExpect(jsonPath("$.lastUpdateDateTime").value("2020-01-02T13:58:56"))
                .andExpect(jsonPath("$.isHelpful").value("true"))
                .andExpect(jsonPath("$.lastAnswerName").value("Tot"))
                .andExpect(jsonPath("$.isHelpful").value("true"))
                .andExpect(jsonPath("$.length()").value("14"));
    }

    @Test
    void getQuestionNotPresent() throws Exception {
        this.mockMvc.perform(get("/api/user/question/1234"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("No question with ID 1234"));
    }

    @Test
    void searchNotNumberForQuestionId() throws Exception {
        this.mockMvc.perform(get("/api/user/question/abc"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getQuestionWithDeleteTrue() throws Exception {
        this.mockMvc.perform(get("/api/user/question/4"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("No question with ID 4"));
    }

    @Test
    void deleteQuestionByIdNoPresent() throws Exception {
        this.mockMvc.perform(delete("/api/user/question/1234"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Can't find Question with ID 1234"));
    }

    @Test
    void deleteQuestionByIdIsPresentWithoutAnswer() throws Exception {
        this.mockMvc.perform(delete("/api/user/question/3"))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/user/question/3"))
                .andDo(print())
                .andExpect(status().isBadRequest());
        this.mockMvc.perform(get("/api/user/question/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("7"));
    }

    @Test
    void deleteQuestionByIdIsPresentWithAnswer() throws Exception {
        this.mockMvc.perform(delete("/api/user/question/2"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Can't delete question with ID 2. Question has answer"));
    }

    @Test
    void getQuestionListByUserId() throws Exception {
        this.mockMvc.perform(get("/api/user/question/by-user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].userDto.id").value("1"))
                .andExpect(jsonPath("$.[1].userDto.id").value("1"))
                .andExpect(jsonPath("$.[2].userDto.id").value("1"));
    }

    @Test
    void getQuestionListByNotPresentUserId() throws Exception {
        this.mockMvc.perform(get("/api/user/question/by-user/5"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getQuestionListByUserIdNotNumber() throws Exception {
        this.mockMvc.perform(get("/api/user/question/by-user/abc"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionCorrect() throws Exception {
        this.mockMvc.perform(put("/api/user/question/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("13"))
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.title").value("Question3 title New"))
                .andExpect(jsonPath("$.description").value("Question3 description New"));
    }

    @Test
    void updateQuestionDifferentUlrAndId() throws Exception {
        this.mockMvc.perform(put("/api/user/question/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionWrongUrlAndId() throws Exception {
        this.mockMvc.perform(put("/api/user/question/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 10," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionWrongIdNull() throws Exception {
        this.mockMvc.perform(put("/api/user/question/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": null," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionWrongIdNotNumber() throws Exception {
        this.mockMvc.perform(put("/api/user/question/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": \"abc\"," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionWrongTitleNull() throws Exception {
        this.mockMvc.perform(put("/api/user/question/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": null," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionWrongTitleEmpty() throws Exception {
        this.mockMvc.perform(put("/api/user/question/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": \"               \"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateQuestionWrongDescription() throws Exception {
        this.mockMvc.perform(put("/api/user/question/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": null" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void test_Count_Of_Questions_In_DB() throws Exception {
        this.mockMvc.perform(get("/api/user/question/pagination?page=1&size=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value(8));
    }

    @Test
    void test_Size_Rand_Param_From_QuestionController() throws Exception {
        this.mockMvc.perform(get("/api/user/question/pagination?page=1&size=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(3));
    }

    @Test
    void test_Page_Rand_Param_From_QuestionController() throws Exception {
        this.mockMvc.perform(get("/api/user/question/pagination?page=7&size=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(1));
    }


    @Test
    void test_Size_Zero_Param_From_QuestionController_Method_getPaginationQuestion() throws Exception {
        this.mockMvc.perform(get("/api/user/question/pagination?page=0&size=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void test_With_Negative_Param() throws Exception {
        this.mockMvc.perform(get("/api/question/pagination?page=-1&size=-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void toVoteForQuestionSuccessPositive() throws Exception {
        this.mockMvc.perform(put("/api/user/question/1/upVote?userId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("13"))
                .andExpect(jsonPath("$.id").value("1"));
//                .andExpect(jsonPath("$.countValuable").value("2"));
    }

    @Test
    void toVoteForQuestionNegativePositive() throws Exception {
        this.mockMvc.perform(put("/api/user/question/1/downVote"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("13"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.countValuable").value("0"));
    }

    @Test
    void toVoteForQuestionNotSuccessWrongQuestionId() throws Exception {
        this.mockMvc.perform(put("/api/user/question/10/upVote"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Can't find Question with ID 10"));
    }
}
