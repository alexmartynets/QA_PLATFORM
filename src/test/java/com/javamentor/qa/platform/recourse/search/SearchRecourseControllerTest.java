package com.javamentor.qa.platform.recourse.search;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataSet(value = {"searchDatasets/roleSearch.yml", "searchDatasets/usersSearch.yml", "searchDatasets/answerSearch.yml", "searchDatasets/questionSearch.yml", "searchDatasets/tagSearch.yml", "searchDatasets/question_has_tagSearch.yml"}, cleanBefore = true, cleanAfter = true)
public class SearchRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchUserIsPresentAndHaveQuestion() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=user:2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("3"))
                .andExpect(jsonPath("$.length()").value("1"));
    }

    @Test
    void searchUserNotPresent() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=user:234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchNotNumberForUserId() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=user:q"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchUserHaveNotQuestion() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=user:3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("0"));
    }

    @Test
    void searchAnswerHas2() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=answers:2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("1"));
    }

    @Test
    void searchAnswerHas0AndMore() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=answers:0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchAnswerNotDigitParam() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=answers:q"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchAnswerDigitMoreThanPresent() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=answers:10000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("0"));
    }

    @Test
    void searchHelpfulYes() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=helpful:yes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("1"));
    }

    @Test
    void searchHelpfulNo() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=helpful:no"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("2"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchHelpfulAnyOtherData() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=helpful:qwerty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchVotesFrom0() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=votes:0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchVotesMax() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=votes:3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("2"));
    }

    @Test
    void searchVotesNotDigit() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=votes:q"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchVotesMoreThanMax() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=votes:300"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("0"));
    }

    @Test
    void searchTagIsPresent() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=[java]"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchTagIsPresentWithoutBrackets() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=java"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"));
    }

    @Test
    void searchTagAnyData() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=[qwerty]"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("0"));
    }

    @Test
    void searchCommonJsonTestForAllFieldsFromUser() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=user:2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("3"))
                .andExpect(jsonPath("$.[0].title").value("Question number three"))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("Tot"))
                .andExpect(jsonPath("$.[0].tags.length()").value("1"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value("1"))
                .andExpect(jsonPath("$.[0].viewCount").value("2"))
                .andExpect(jsonPath("$.[0].countAnswer").value("0"))
                .andExpect(jsonPath("$.[0].countValuable").value("2"))
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-01-20T13:58:56"))
                .andExpect(jsonPath("$.[0].isHelpful").value("false"))
                .andExpect(jsonPath("$.[0].description").value("some description for next question number three"));
    }

    @Test
    void searchCommonJsonTestForAllFieldsFromVotes() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=votes:3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("2"))
                .andExpect(jsonPath("$.[0].title").value("Question number two"))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.[0].tags.length()").value("1"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value("2"))
                .andExpect(jsonPath("$.[0].viewCount").value("5"))
                .andExpect(jsonPath("$.[0].countAnswer").value("1"))
                .andExpect(jsonPath("$.[0].countValuable").value("3"))
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-01-10T13:58:56"))
                .andExpect(jsonPath("$.[0].isHelpful").value("false"))
                .andExpect(jsonPath("$.[0].description").value("some description for second question"));
    }

    @Test
    void searchCommonJsonTestForAllFieldsFromHelpful() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=helpful:yes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value("Question number one"))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.[0].tags.length()").value("1"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value("2"))
                .andExpect(jsonPath("$.[0].viewCount").value("3"))
                .andExpect(jsonPath("$.[0].countAnswer").value("2"))
                .andExpect(jsonPath("$.[0].countValuable").value("1"))
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-01-01T13:58:56"))
                .andExpect(jsonPath("$.[0].isHelpful").value("true"))
                .andExpect(jsonPath("$.[0].description").value("some description for first question"));
    }

    @Test
    void searchCommonJsonTestForAllFieldsFromAnswer() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=answers:2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value("Question number one"))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.[0].tags.length()").value("1"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value("2"))
                .andExpect(jsonPath("$.[0].viewCount").value("3"))
                .andExpect(jsonPath("$.[0].countAnswer").value("2"))
                .andExpect(jsonPath("$.[0].countValuable").value("1"))
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-01-01T13:58:56"))
                .andExpect(jsonPath("$.[0].isHelpful").value("true"))
                .andExpect(jsonPath("$.[0].description").value("some description for first question"));
    }

    @Test
    void searchCommonJsonTestForAllFieldsFromTag() throws Exception {
        this.mockMvc.perform(get("/api/user/search?search=[java]"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andExpect(jsonPath("$.[0].id").value("3"))
                .andExpect(jsonPath("$.[0].title").value("Question number three"))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("Tot"))
                .andExpect(jsonPath("$.[0].tags.length()").value("1"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value("1"))
                .andExpect(jsonPath("$.[0].viewCount").value("2"))
                .andExpect(jsonPath("$.[0].countAnswer").value("0"))
                .andExpect(jsonPath("$.[0].countValuable").value("2"))
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-01-20T13:58:56"))
                .andExpect(jsonPath("$.[0].isHelpful").value("false"))
                .andExpect(jsonPath("$.[0].description").value("some description for next question number three"));
    }
}