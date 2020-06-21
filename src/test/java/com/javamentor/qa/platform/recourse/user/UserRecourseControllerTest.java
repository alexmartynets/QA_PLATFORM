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


@DataSet(value = {
        "users/role.yml",
        "users/users.yml",
        "users/reputation.yml",
        "users/editor.yml",
        "users/moderator.yml"}, cleanBefore = true, cleanAfter = true)
public class UserRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addUser() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": null," +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": 1111," +
                        "\"about\": \"about admin\"," +
                        "\"city\": \"Moscow\"," +
                        "\"reputationCount\": 0," +
                        "\"persistDateTime\": \"2020-06-20T18:51:11.548\"," +
                        "\"lastUpdateDateTime\": \"2020-06-20T18:51:11.548\" " +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Админ"));
    }

    @Test
    void findAllUsers() throws Exception {
        this.mockMvc.perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.length()").value("5"));
    }

    @Test
    void updateUser() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", 6L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 6," +
                        "\"fullName\": \"Василий Петрович Рощин\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"1111\"," +
                        "\"about\": \"about admin\"," +
                        "\"city\": \"Moscow\"," +
                        "\"reputationCount\": \"0\"," +
                        "\"persistDateTime\": \"2020-06-20T18:51:11.548\"," +
                        "\"lastUpdateDateTime\": \"2020-06-20T18:51:11.548\" " +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("6"))
                .andExpect(jsonPath("$.fullName").value("Василий"));
    }

    @Test
    void findUser() throws Exception {
        this.mockMvc.perform(get("/api/user/{id}", 5L))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.fullName").value("Иван"))
                .andExpect(jsonPath("$.email").value("ivan@com"))
                .andExpect(jsonPath("$.password").value("5678"))
                .andExpect(jsonPath("$.role").value(1))
                .andExpect(jsonPath("$.about").value("description 2"))
                .andExpect(jsonPath("$.city").value("SPB"));
    }

    @Test
    void getListNewUsersByReputation() throws Exception {
        this.mockMvc.perform(get("/api/user/new/reputation?count=5&page=1&weeks=12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getListUsersByCreationDate() throws Exception {
        this.mockMvc.perform(get("/api/user/new?count=5&page=1&weeks=12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getListUsersByReputation() throws Exception {
        this.mockMvc.perform(get("/api/user/reputation?count=5&page=1&weeks=12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getListUsersByVoice() throws Exception {
        this.mockMvc.perform(get("/api/user/voice?count=5&page=1&weeks=12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getListUsersByQuantityEditedText() throws Exception {
        this.mockMvc.perform(get("/api/user/editor?count=5&page=1&weeks=12"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getListUsersByModerator() throws Exception {
        this.mockMvc.perform(get("/api/user/moderator"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
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

