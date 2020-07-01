package com.javamentor.qa.platform.recourse.tag;

import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TagResourceControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllMainTagsSortedByFrequency() throws Exception {
        this.mockMvc.perform(get("/api/tags"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getRelatedTags() throws Exception {
        this.mockMvc.perform(get("/api/tags"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
