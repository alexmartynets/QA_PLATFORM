package com.javamentor.qa.platform.recourse.answer;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataSet(value = {"role.yml", "users.yml", "answer.yml", "question.yml", "tag.yml", "question_has_tag.yml"})
public class AnswerResourceControllerTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
//    @DataSet(value = {"role.yml", "users.yml", "answer.yml", "question.yml", "tag.yml", "question_has_tag.yml"}, cleanAfter = true)
    public void getAllAnswerDtoByQuestionID1SortNew() throws Exception {
        mockMvc.perform(get("/api/user/question/1/answer")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].questionId").value(1))
                .andExpect(jsonPath("$.[0].htmlBody").value("first answer for Q1"))
                .andExpect(jsonPath("$.[0].isDeleted").value(false))
                .andExpect(jsonPath("$.[0].isHelpful").value(true))
                .andExpect(jsonPath("$.[0].dateAcceptTime").exists())
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))
                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))
                .andExpect(jsonPath("$.[0].countValuable").value(10))
                .andExpect(jsonPath("$.[0].userDto.id").value(1))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("first user"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value(5))
                .andExpect(jsonPath("$.[0].userDto.image").doesNotExist())//изменить после реализации
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].questionId").value(1))
                .andExpect(jsonPath("$.[1].htmlBody").value("second answer for Q1"))
                .andExpect(jsonPath("$.[1].isDeleted").value(false))
                .andExpect(jsonPath("$.[1].isHelpful").value(false))
                .andExpect(jsonPath("$.[1].dateAcceptTime").doesNotExist())
                .andExpect(jsonPath("$.[1].persistDateTime").value("2020-05-28T14:00:00"))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[2].questionId").value(1))
                .andExpect(jsonPath("$.[2].htmlBody").value("third answer for Q1"))
                .andExpect(jsonPath("$.[2].isDeleted").value(false))
                .andExpect(jsonPath("$.[2].persistDateTime").value("2020-05-27T13:30:00"))
                .andExpect(jsonPath("$.[2].updateDateTime").value("2020-05-27T13:40:00"))
                .andExpect(jsonPath("$.[3]").doesNotExist());
    }

    @Test
//    @DataSet(value = {"role.yml", "users.yml", "answer.yml", "question.yml", "tag.yml", "question_has_tag.yml"}, cleanAfter = true, cleanBefore = true)
    public void getAllAnswerDtoByQuestionIDSortCount() throws Exception {
        mockMvc.perform(get("/api/user/question/1/answer/sort/count")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].questionId").value(1))
                .andExpect(jsonPath("$.[0].htmlBody").value("first answer for Q1"))
                .andExpect(jsonPath("$.[0].isDeleted").value(false))
                .andExpect(jsonPath("$.[0].isHelpful").value(true))
                .andExpect(jsonPath("$.[0].dateAcceptTime").exists())
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))
                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))
                .andExpect(jsonPath("$.[0].countValuable").value(10))
                .andExpect(jsonPath("$.[0].userDto.id").value(1))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("first user"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value(5))
                .andExpect(jsonPath("$.[0].userDto.image").doesNotExist())//изменить после реализации
                .andExpect(jsonPath("$.[1].countValuable").value(9))
                .andExpect(jsonPath("$.[2].countValuable").value(8))
                .andExpect(jsonPath("$.[3]").doesNotExist());
    }

    @Test
//    @DataSet(value = {"role.yml", "users.yml", "answer.yml", "question.yml", "tag.yml", "question_has_tag.yml"}, cleanAfter = true, cleanBefore = true)
    public void getAllAnswerDtoByQuestionIDSortDate() throws Exception {
        mockMvc.perform(get("/api/user/question/1/answer/sort/date")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].questionId").value(1))
                .andExpect(jsonPath("$.[0].htmlBody").value("first answer for Q1"))
                .andExpect(jsonPath("$.[0].isDeleted").value(false))
                .andExpect(jsonPath("$.[0].isHelpful").value(true))
                .andExpect(jsonPath("$.[0].dateAcceptTime").value("2020-05-29T14:00:00"))
                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))
                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))
                .andExpect(jsonPath("$.[0].countValuable").value(10))
                .andExpect(jsonPath("$.[0].userDto.id").value(1))
                .andExpect(jsonPath("$.[0].userDto.fullName").value("first user"))
                .andExpect(jsonPath("$.[0].userDto.reputationCount").value(5))
                .andExpect(jsonPath("$.[0].userDto.image").doesNotExist())//изменить после реализации
                .andExpect(jsonPath("$.[1].persistDateTime").value("2020-05-27T13:30:00"))
                .andExpect(jsonPath("$.[2].persistDateTime").value("2020-05-28T14:00:00"))
                .andExpect(jsonPath("$.[3]").doesNotExist());
    }
}
