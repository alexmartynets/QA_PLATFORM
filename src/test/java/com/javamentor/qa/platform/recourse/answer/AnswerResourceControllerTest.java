package com.javamentor.qa.platform.recourse.answer;import com.github.database.rider.core.api.dataset.DataSet;import com.javamentor.qa.platform.AbstractIntegrationTest;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;@DataSet(value = {"AnswerApiTest/role.yml",        "AnswerApiTest/users.yml",        "AnswerApiTest/answer_u.yml",        "AnswerApiTest/question_u.yml",        "AnswerApiTest/tag.yml",        "AnswerApiTest/question_has_tag.yml"}, cleanBefore = true)public class AnswerResourceControllerTest extends AbstractIntegrationTest {    @Autowired    private MockMvc mockMvc;    @Test    public void getAllAnswerDtoByQuestionID1SortNew() throws Exception {        mockMvc.perform(get("/api/user/question/1/answer")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isOk())                .andExpect(jsonPath("$.length()").value(3))                .andExpect(jsonPath("$.[0].id").value(1))                .andExpect(jsonPath("$.[0].questionId").value(1))                .andExpect(jsonPath("$.[0].htmlBody").value("first answer for Q1"))                .andExpect(jsonPath("$.[0].isDeleted").value(false))                .andExpect(jsonPath("$.[0].isHelpful").value(true))                .andExpect(jsonPath("$.[0].dateAcceptTime").exists())                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))                .andExpect(jsonPath("$.[0].countValuable").value(10))                .andExpect(jsonPath("$.[0].userDto.id").value(1))                .andExpect(jsonPath("$.[0].userDto.fullName").value("first user"))                .andExpect(jsonPath("$.[0].userDto.reputationCount").value(5))                .andExpect(jsonPath("$.[0].userDto.image").doesNotExist())//изменить после реализации                .andExpect(jsonPath("$.[1].id").value(2))                .andExpect(jsonPath("$.[1].questionId").value(1))                .andExpect(jsonPath("$.[1].htmlBody").value("second answer for Q1"))                .andExpect(jsonPath("$.[1].isDeleted").value(false))                .andExpect(jsonPath("$.[1].isHelpful").value(false))                .andExpect(jsonPath("$.[1].dateAcceptTime").doesNotExist())                .andExpect(jsonPath("$.[1].persistDateTime").value("2020-05-28T14:00:00"))                .andExpect(jsonPath("$.[2].id").value(3))                .andExpect(jsonPath("$.[2].questionId").value(1))                .andExpect(jsonPath("$.[2].htmlBody").value("third answer for Q1"))                .andExpect(jsonPath("$.[2].isDeleted").value(false))                .andExpect(jsonPath("$.[2].persistDateTime").value("2020-05-27T13:30:00"))                .andExpect(jsonPath("$.[2].updateDateTime").value("2020-05-27T13:40:00"))                .andExpect(jsonPath("$.[3]").doesNotExist());    }    @Test    public void getAllAnswerDtoByQuestionIDSortCount() throws Exception {        mockMvc.perform(get("/api/user/question/1/answer/sort/count")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isOk())                .andExpect(jsonPath("$.length()").value(3))                .andExpect(jsonPath("$.[0].id").value(1))                .andExpect(jsonPath("$.[0].questionId").value(1))                .andExpect(jsonPath("$.[0].htmlBody").value("first answer for Q1"))                .andExpect(jsonPath("$.[0].isDeleted").value(false))                .andExpect(jsonPath("$.[0].isHelpful").value(true))                .andExpect(jsonPath("$.[0].dateAcceptTime").exists())                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))                .andExpect(jsonPath("$.[0].countValuable").value(10))                .andExpect(jsonPath("$.[0].userDto.id").value(1))                .andExpect(jsonPath("$.[0].userDto.fullName").value("first user"))                .andExpect(jsonPath("$.[0].userDto.reputationCount").value(5))                .andExpect(jsonPath("$.[0].userDto.image").doesNotExist())//изменить после реализации                .andExpect(jsonPath("$.[1].countValuable").value(9))                .andExpect(jsonPath("$.[2].countValuable").value(8))                .andExpect(jsonPath("$.[3]").doesNotExist());    }    @Test    public void getAllAnswerDtoByQuestionIDSortDate() throws Exception {        mockMvc.perform(get("/api/user/question/1/answer/sort/date")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isOk())                .andExpect(jsonPath("$.length()").value(3))                .andExpect(jsonPath("$.[0].id").value(1))                .andExpect(jsonPath("$.[0].questionId").value(1))                .andExpect(jsonPath("$.[0].htmlBody").value("first answer for Q1"))                .andExpect(jsonPath("$.[0].isDeleted").value(false))                .andExpect(jsonPath("$.[0].isHelpful").value(true))                .andExpect(jsonPath("$.[0].dateAcceptTime").value("2020-05-29T14:00:00"))                .andExpect(jsonPath("$.[0].persistDateTime").value("2020-05-26T13:00:00"))                .andExpect(jsonPath("$.[0].updateDateTime").value("2020-05-29T14:00:00"))                .andExpect(jsonPath("$.[0].countValuable").value(10))                .andExpect(jsonPath("$.[0].userDto.id").value(1))                .andExpect(jsonPath("$.[0].userDto.fullName").value("first user"))                .andExpect(jsonPath("$.[0].userDto.reputationCount").value(5))                .andExpect(jsonPath("$.[0].userDto.image").doesNotExist())//изменить после реализации                .andExpect(jsonPath("$.[1].persistDateTime").value("2020-05-27T13:30:00"))                .andExpect(jsonPath("$.[2].persistDateTime").value("2020-05-28T14:00:00"))                .andExpect(jsonPath("$.[3]").doesNotExist());    }    @Test    public void addNewAnswer_CREATED() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isCreated())                .andExpect(content().contentType(MediaType.APPLICATION_JSON))                .andExpect(jsonPath("$.questionId").value(1))                .andExpect(jsonPath("$.userDto.id").value(1));    }    @Test    public void addNewAnswer_UserId_MustBeNotNull() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": null}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())//                .andExpect(status().isBadRequest())                .andExpect(content().string("User id must be not null on create."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_UserId_MustBeMoreThen0() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 0}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())//                .andExpect(status().isBadRequest())                .andExpect(content().string("User id must be > 0 on create or update."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_AnswerId_mustBeNull() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'id' must be null on create. (AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_AnswerId_mustBeNotLetter() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": \"w\"," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Cannot deserialize value of type `java.lang.Long` from String \"w\""))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_QuestionId_cantBeNull() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": null," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'questionId' can`t be null.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_QuestionId_cantBeLetter() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": \"nll\"," +                        "\"questionId\": null," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Cannot deserialize value of type `java.lang.Long` from String \"nll\""))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_htmlBody_cantBeNull() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": null," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'htmlBody' must not be null and must contain at least one non-whitespace character.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_htmlBody_cantBeEmpty() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \" \"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'htmlBody' must not be null and must contain at least one non-whitespace character.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_countValuable_mustBe_0() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": -1," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'countValuable' must be a number '>=0' on create. (AnswerDto.class)."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_countValuable_mustBe_aDigit() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": \"w\"," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Cannot deserialize value of type `java.lang.Integer` from String \"w\""))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_isHelpful_mustBe_false() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": true," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'isHelpful' must be false on create. (AnswerDto.class)."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_isHelpful_mustBe_NotLetter() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": \"p\"," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Cannot deserialize value of type `java.lang.Boolean` from String \"p\""))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_isDeleted_mustBe_false() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": true," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'isDeleted' must be false on create. (AnswerDto.class)."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_persistDate_mustBe_null() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"persistDateTime\": \"2020-06-09T17:11:00\"," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'persistDateTime' must be null on create. (AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_dateAcceptTime_mustBe_null() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"dateAcceptTime\": \"2020-06-09T17:11:00\"," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'dateAcceptTime' must be null on create. (AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void addNewAnswer_updateDateTime_mustBe_null() throws Exception {        mockMvc.perform(post("/api/user/question/1/answer")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"updateDateTime\": \"2020-06-09T17:11:00\"," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'updateDateTime' must be null on create. (AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_UPDATED() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"update answer for question 1TEST2\"," +                        "\"countValuable\": 11," +                        "\"isHelpful\": true," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isOk())                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_id_mustBe_NotNull() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": null," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"update answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'id' can`t be null on update.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_id_mustBe_moreThen0() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 0," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"update answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'id' must not be null and must contain at least one non-whitespace character.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_questionId_mustBe_NotNull() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": null," +                        "\"htmlBody\": \"update answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'questionId' can`t be null.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_UserId_mustBe_MoreThen0() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 0}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("User id must be > 0 on create or update."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_UserId_mustBe_NotNull() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"answer for question 1TEST2\"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": null}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("User id can not be null on update."))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_htmlBody_mustBe_NotNull() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": 1," +                        "\"htmlBody\": null," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'htmlBody' must not be null and must contain at least one non-whitespace character.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void updateAnswer_htmlBody_cantBeEmpty() throws Exception {        mockMvc.perform(put("/api/user/question/1/answer/1")                .contentType(MediaType.APPLICATION_JSON)                .content("{\"id\": 1," +                        "\"questionId\": 1," +                        "\"htmlBody\": \"  \"," +                        "\"countValuable\": 0," +                        "\"isHelpful\": false," +                        "\"isDeleted\": false," +                        "\"userDto\": {" +                        "\"id\": 1}}")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().string("Field 'htmlBody' must not be null and must contain at least one non-whitespace character.(AnswerDto.class)"))                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }    @Test    public void deleteAnswer_() throws Exception {        mockMvc.perform(delete("/api/user/question/1/answer/t")                .accept(MediaType.APPLICATION_JSON))                .andDo(print())                .andExpect(status().isBadRequest())                .andExpect(content().contentType(MediaType.APPLICATION_JSON));    }}