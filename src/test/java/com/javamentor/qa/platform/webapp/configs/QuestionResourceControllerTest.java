package com.javamentor.qa.platform.webapp.configs;

import com.javamentor.qa.platform.webapp.configs.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest                 //(classes = Application.class) if the directory is different
@AutoConfigureMockMvc
public class QuestionResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testQuestion() throws Exception{
        this.mockMvc.perform(post("/api/question/pagination?page=1&size=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Question2")))
                .andExpect(content().string(containsString("Question1")))
                .andExpect(content().string(containsString("Question3")));
    }
}
