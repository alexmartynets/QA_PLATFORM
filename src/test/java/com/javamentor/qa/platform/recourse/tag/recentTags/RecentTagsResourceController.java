package com.javamentor.qa.platform.recourse.tag.recentTags;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataSet(value = {"tagsDatasets/recentTags/tag.yml", "tagsDatasets/recentTags/questions.yml",
        "tagsDatasets/recentTags/question_has_tag.yml", "tagsDatasets/recentTags/users.yml",
        "tagsDatasets/recentTags/role.yml", "tagsDatasets/recentTags/related_tag.yml"}, cleanBefore = true)
public class RecentTagsResourceController extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllMainTagsSortedByFrequency() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/mainTags")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"));
    }

    @Test
    void getRelatedTags() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/relatedTags/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("2"));
    }

    @Test
    void getRelatedTagsTest() throws Exception {
        this.mockMvc.perform(get("/api/user/tag/relatedTags/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("1"));
    }
}
