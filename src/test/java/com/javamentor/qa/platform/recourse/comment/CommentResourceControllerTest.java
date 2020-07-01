package com.javamentor.qa.platform.recourse.comment;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DataSet(value = {
        "comment/role.yml",
        "comment/users.yml",
        "comment/comment.yml",
        "comment/comment_answer.yml",
        "comment/comment_question.yml",
        "comment/answer_c.yml",
        "comment/question_c.yml"}, cleanBefore = true, cleanAfter = true)
class CommentResourceControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_Comments_To_Question() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"));
    }

    @Test
    void get_Comments_To_Question_Question_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 18))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("question with ID: 18 not found"));
    }

    @Test
    void get_Comments_To_Answer() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"));
    }

    @Test
    void get_Comments_To_Answer_Answer_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 19))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("answer with ID: 19 not found"));
    }

    @Test
    void save_Comment_Question() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"text of the new comment to question\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 7" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("text of the new comment to question"))
                .andExpect(jsonPath("$.userId").value("7"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Question_Short_Text() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 1" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must be greater than 10 characters CommentDto.class"));
    }

    @Test
    void save_Comment_Question_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"             \"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must not consist of spaces CommentDto.class"));
    }

    @Test
    void save_Comment_Question_No_User_Id() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": null," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("author 'id' Must not be null when creating CommentDto.class"));
    }

    @Test
    void save_Comment_Question_No_Type_Comment() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"userId\": 1" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'type' Must not be null when creating and updating CommentDto.class"));
    }

    @Test
    void save_Comment_Question_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 7" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("У экземпляра Comment, " +
                        "связанного с CommentQuestion, поле commentType должно принимать значение CommentType.QUESTION"));
    }

    @Test
    void save_Comment_Question_User_Saves_Comment_Again() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("You can leave only one comment in question"));
    }

    @Test
    void save_Comment_Question_User_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 100" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("user with ID: 100 not found"));
    }

    @Test
    void save_Comment_Question_Question_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 12)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 1" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("question with ID: 12 not found"));
    }

    @Test
    void save_Comment_Answer() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"text of the new comment to answer\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("text of the new comment to answer"))
                .andExpect(jsonPath("$.userId").value("3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_Short_Text() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must be greater than 10 characters CommentDto.class"));
    }

    @Test
    void save_Comment_Answer_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"             \"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 4" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must not consist of spaces CommentDto.class"));
    }

    @Test
    void save_Comment_Answer_No_User_Id() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": null" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("author 'id' Must not be null when creating CommentDto.class"));
    }

    @Test
    void save_Comment_Answer_No_Type_Comment() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'type' Must not be null when creating and updating CommentDto.class"));
    }

    @Test
    void save_Comment_Answer_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 6" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("У экземпляра Comment, " +
                        "связанного с CommentAnswer, поле commentType должно принимать значение CommentType.ANSWER"));
    }

    @Test
    void save_Comment_Answer_User_Saves_Comment_Again() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 1" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Only one comment can be left under the answer"));
    }

    @Test
    void save_Comment_Answer_User_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 28" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("user with ID: 28 not found"));
    }

    @Test
    void save_Comment_Answer_Answer_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 13)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("answer with ID: 13 not found"));
    }

    @Test
    void update_Comment_Question() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"new comment text to answer\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("new comment text to answer"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question_Short_Text() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"Comment\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must be greater than 10 characters CommentDto.class"));
    }

    @Test
    void update_Comment_Question_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"             \"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must not consist of spaces CommentDto.class"));
    }

    @Test
    void update_Comment_Question_No_Id_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": null," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'id' Must not accept null values when updating CommentDto.class"));
    }

    @Test
    void update_Comment_Question_Comment_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 22," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("comment with ID: 22 not found"));
    }

    @Test
    void update_Comment_Question_No_Type_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"Comment test text\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'type' Must not be null when creating and updating CommentDto.class"));
    }

    @Test
    void update_Comment_Answer() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"new comment text to answer\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("new comment text to answer"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer_Short_Text() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"Comment\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must be greater than 10 characters CommentDto.class"));
    }

    @Test
    void update_Comment_Answer_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"             \"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'text' Must not consist of spaces CommentDto.class"));
    }

    @Test
    void update_Comment_Answer_No_Id_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": null," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'id' Must not accept null values when updating CommentDto.class"));
    }

    @Test
    void update_Comment_Answer_Answer_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 27," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("comment with ID: 27 not found"));
    }

    @Test
    void update_Comment_Answer_No_Type_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"Comment test text\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("'type' Must not be null when creating and updating CommentDto.class"));
    }
}
