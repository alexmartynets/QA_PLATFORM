package com.javamentor.qa.platform.recourse.tag;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@DataSet(value = {"tagsDatasets/role.yml", "tagsDatasets/users.yml", "tagsDatasets/answer.yml", "tagsDatasets/question.yml", "tagsDatasets/tag.yml", "tagsDatasets/question_has_tag.yml"}, cleanBefore = true)
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
                .andExpect(jsonPath("$.value[0].id").value("1"));
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

}
