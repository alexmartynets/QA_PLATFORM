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

@DataSet(value = {"tagsDatasets/roleSearch.yml", "tagsDatasets/usersSearch.yml", "tagsDatasets/answerSearch.yml", "tagsDatasets/questionSearch.yml", "tagsDatasets/tagSearch.yml", "tagsDatasets/question_has_tagSearch.yml"}, cleanBefore = true, cleanAfter = true)
public class TagResourceControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_all_tags_sort_popular() throws Exception {
        this.mockMvc.perform(get("/api/user/tag"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
                .andExpect(jsonPath("$.[0].id").value("9"))
                .andExpect(jsonPath("$.length()").value("16"));
    }
}
