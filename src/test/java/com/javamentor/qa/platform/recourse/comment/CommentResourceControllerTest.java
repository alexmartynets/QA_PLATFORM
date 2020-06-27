package com.javamentor.qa.platform.recourse.comment;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataSet(value = {
        "comment/role.yml",
        "comment/users.yml",
        "comment/comment.yml",
        "comment/comment_answer.yml",
        "comment/comment_question.yml",
        "comment/answer_c.yml",
        "comment/question_c.yml"}, cleanBefore = true, cleanAfter = true)
class CommentResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_Comments_To_Question() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(1));
    }

    @Test
    void get_Comments_To_Answer() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value.size()").value(1));
    }

    @Test
    void save_Comment_Question_Short_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void save_Comment_Question_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Question_No_User_Id() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Question_No_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Question_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Question_User_Saves_Comment_Again() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Question_User_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(get("/api/user/question/{questionId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_Short_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void save_Comment_Answer_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_No_User_Id() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_No_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_User_Saves_Comment_Again() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void save_Comment_Answer_User_Id_Not_Exist() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/{answerId}/comment", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question_Short_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update_Comment_Question_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question_No_Id_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question_No_User_Id() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question_No_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Question_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/question/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 3,\n" +
                        "\"text\": \"Comment 3 text\",\n" +
                        "\"commentType\": \"QUESTION\",\n" +
                        "\"userId\": 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer_Short_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update_Comment_Answer_Spaces_Instead_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer_No_Id_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer_No_User_Id() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer_No_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_Comment_Answer_Not_Correct_Type_Comment() throws Exception {
        this.mockMvc.perform(get("/api/user/answer/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\": 2,\n" +
                        "\"text\": \"Comment 2 text\",\n" +
                        "\"commentType\": \"ANSWER\",\n" +
                        "\"userId\": 2\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
