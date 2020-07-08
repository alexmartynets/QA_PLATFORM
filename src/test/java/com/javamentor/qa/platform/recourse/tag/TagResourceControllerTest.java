package com.javamentor.qa.platform.recourse.tag;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@DataSet(value = {"tagsDatasets/role.yml", "tagsDatasets/users.yml", "tagsDatasets/answer.yml", "tagsDatasets/question.yml", "tagsDatasets/tag.yml", "tagsDatasets/question_has_tag.yml"}, cleanBefore = true)
public class TagResourceControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_all_tags_sort_popular() throws Exception {
        this.mockMvc.perform(get("/api/user/tag?pageSize=16&pageNumber=1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("16"))
                .andExpect(jsonPath("$.value.length()").value("16"))
                .andExpect(jsonPath("$.value[0].id").value("1"))
                .andExpect(jsonPath("$.value[0].description").value("Java the best language"))
                .andExpect(jsonPath("$.value[0].persistDateTime").value("2019-12-28T13:58:56"))
                .andExpect(jsonPath("$.value[0].questionCount").value("6"))
//                .andExpect(jsonPath("$.value[0].questionTodayCount").value("1"))
//                .andExpect(jsonPath("$.value[0].questionWeekCount").value("2"))
//                .andExpect(jsonPath("$.value[0].questionMonthCount").value("3"))
                .andExpect(jsonPath("$.value[0].questionYearCount").value("6"));
    }

    @Test
    void get_all_tags_sort_name() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/name?pageSize=16&pageNumber=1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("16"))
                .andExpect(jsonPath("$.value.length()").value("16"))
                .andExpect(jsonPath("$.value[0].id").value("8"));
    }

    @Test
    void get_all_tags_sort_new() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/new?pageSize=16&pageNumber=1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("16"))
                .andExpect(jsonPath("$.value.length()").value("16"))
                .andExpect(jsonPath("$.value[0].id").value("16"));
    }

    @Test
    void get_all_tags_not_number_param() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/new?pageSize=16&pageNumber=rrr")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Значения не должны быть символьными, только числовые!"));
    }

    @Test
    void get_all_tags_search_java() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/search?pageSize=3&pageNumber=1&tagName=java")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("1"))
                .andExpect(jsonPath("$.value.length()").value("1"))
                .andExpect(jsonPath("$.value[0].id").value("1"));
    }

    @Test
    void get_all_tags_search_j() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/search?pageSize=16&pageNumber=1&tagName=j")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("4"))
                .andExpect(jsonPath("$.value.length()").value("4"))
                .andExpect(jsonPath("$.value[0].id").value("1"))
                .andExpect(jsonPath("$.value[1].id").value("2"))
                .andExpect(jsonPath("$.value[2].id").value("8"))
                .andExpect(jsonPath("$.value[3].id").value("10"));
    }

    @Test
    void addMainTag() throws Exception {
        this.mockMvc.perform(post("/api/user/tag")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": null," +
                        "\"name\": \"newLanguage\"," +
                        "\"description\": \"new language description\"" +
                        "}"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addChildTag() throws Exception {
        this.mockMvc.perform(post("/api/user/tag/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\": \"childTag\"," +
                        "\"description\": \"child tag description\"" +
                        "}"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateTag() throws Exception {
        this.mockMvc.perform(put("/api/user/tag/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 1," +
                        "\"name\": \"newLanguage\"," +
                        "\"description\": \"new language description\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("newLanguage"))
                .andExpect(jsonPath("$.description").value("new language description"))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteTag() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/tag/delete/{id}", 16))
                .andExpect(status().isAccepted());
    }
}
