package com.javamentor.qa.platform.recourse.comment;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
                .andExpect(status().isOk());
    }

    @Test
    void get_Comments_To_Answer() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void save_Comment_Question() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
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
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Question_No_User_Id() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Question_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Question_User_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(post("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 10" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Answer() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
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
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Answer_No_User_Id() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Answer_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 4" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void save_Comment_Answer_User_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(post("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 28" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_Comment_Question() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
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
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
        ;
    }

    @Test
    void update_Comment_Question_No_Id_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        ;
    }

    @Test
    void update_Comment_Question_No_User_Id() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"QUESTION\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        ;
    }

    @Test
    void update_Comment_Question_No_Type_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"text\": \"Comment test text\"," +
                        "\"userId\": 3" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        ;
    }

    @Test
    void update_Comment_Answer() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        ;
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
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_Comment_Answer_No_Id_Comment() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"," +
                        "\"userId\": 2" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_Comment_Answer_No_User_Id() throws Exception {
        this.mockMvc.perform(put("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"text\": \"Comment test text\"," +
                        "\"commentType\": \"ANSWER\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
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
                .andExpect(status().is4xxClientError());
    }
}
