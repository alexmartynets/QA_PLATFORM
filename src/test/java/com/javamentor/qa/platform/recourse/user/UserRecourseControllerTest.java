package com.javamentor.qa.platform.recourse.user;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DataSet(value = {"user/editor.yml",
        "user/moderator.yml",
        "user/reputation.yml",
        "user/role.yml",
        "user/users.yml"}, cleanBefore = true, cleanAfter = true)
public class UserRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addUser() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findAllUsers() throws Exception {
        this.mockMvc.perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("5"));;
    }

    @Test
    void updateUser() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"title\": \"Question3 title New\"," +
                        "\"description\": \"Question3 description New\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findUser() throws Exception {
        this.mockMvc.perform(get("/api/user/{id}", 5L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListNewUsersByReputation() throws Exception {
        this.mockMvc.perform(get("/api/user/new/reputation?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListUsersByCreationDate() throws Exception {
        this.mockMvc.perform(get("/api/user/new?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListUsersByReputation() throws Exception {
        this.mockMvc.perform(get("/api/user/reputation?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListUsersByVoice() throws Exception {
        this.mockMvc.perform(get("/api/user/voice?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListUsersByQuantityEditedText() throws Exception {
        this.mockMvc.perform(get("/api/user/editor?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListUsersByModerator() throws Exception {
        this.mockMvc.perform(get("/api/user/moderator"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListUsersByNameToSearch() throws Exception {
        this.mockMvc.perform(get("/api/user/find?count=5&page=1&weeks=12&name=Андрей"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}


//    @Test
//    void getListUsersByNameToSearch() throws Exception {
//        mockMvc.perform(get("/api/user/{count}/page/{page}/name?name=Андрей", 3L, 1L))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
////                .andExpect(jsonPath("$.key").value(""))   // List
////                .andExpect(jsonPath("$.value").value(""));  // count
////                .andExpect(jsonPath("$.value", Matchers.is(2)));
//    }


/*        // given
        given(superHeroRepository.getSuperHero(2))
                .willReturn(new SuperHero("Rob", "Mannon", "RobotMan"));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/superheroes/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonSuperHero.write(new SuperHero("Rob", "Mannon", "RobotMan")).getJson()
        );*/


/*    @Test
    public void listStudents() throws Exception {
        MvcResult result =
            mMockMvc.perform(get("/students/list"))
            .andDo(print())
            .andExpect(jsonPath("$", hasSize(mStudentList.size())))
            .andExpect(jsonPath("$.[*].name", hasItems("Peter Venkman", "Egon Spengler", "Raymond Stantz", "Winston Zeddemore")))
            .andExpect(status().isOk())
                .andReturn();

        // alternate way of verifying, convert JSON to list of objects, and verify.
        String content = result.getResponse().getContentAsString();
        List<Student> students = mapFromJsonToList(content, Student.class);
        for (int i = 0; i < students.size(); i++) {
            Student student1 = students.get(i);
            assertEquals(mStudentList.get(i).getName(), student1.getName());
        }
    }*/

