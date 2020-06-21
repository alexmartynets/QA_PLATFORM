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
    void create_User() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Админ"))
                .andExpect(jsonPath("$.email").value("admin@admin.ru"));
    }

    @Test
    void create_User_With_Id() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 2," +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_User_No_Name() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // todo сделать проверку в контролере code 200
    @Test
    void create_User_Name_Null() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"      \"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_User_No_Email() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_User_Email_Null() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"       \"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_User_With_Invalid_Email() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@.ru\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_User_No_Password() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // todo сделать проверку в контролере code 200
    @Test
    void create_User_Password_Null() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"       \"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // todo сделать проверку в контролере code 200
    @Test
    void create_User_With_Invalid_Password() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"Qwerty\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void create_User_With_Role() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Админ\"," +
                        "\"email\": \"admin@admin.ru\"," +
                        "\"password\": \"Qwerty12\"," +
                        "\"role\": 1" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_User() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"fullName\": \"Update\"," +
                        "\"email\": \"Update@email.com\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.fullName").value("Update"))
                .andExpect(jsonPath("$.email").value("Update@email.com"));
    }

    @Test
    void update_User_No_Id() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"fullName\": \"Василий update\"," +
                        "\"email\": \"vasiliy@email.com\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_User_Id_Null() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": \"   \"," +
                        "\"fullName\": \"Василий update\"," +
                        "\"email\": \"vasiliy@email.com\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_User_Invalid_Id_Path_Variable() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", "ads")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 3," +
                        "\"fullName\": \"Василий update\"," +
                        "\"email\": \"vasiliy@email.com\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update_User_Where_Id_Not_Equal_Id_Path_Variable() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}", 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": 4," +
                        "\"fullName\": \"Василий update\"," +
                        "\"email\": \"vasiliy@email.com\"," +
                        "\"password\": \"Qwerty12\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void find_User() throws Exception {
        this.mockMvc.perform(get("/api/user/{id}", 5L))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.fullName").value("Иван"))
                .andExpect(jsonPath("$.email").value("ivan@email.com"))
                .andExpect(jsonPath("$.role").value("user"))
                .andExpect(jsonPath("$.about").value("description 2"))
                .andExpect(jsonPath("$.city").value("SPB"));
    }

    @Test
    void find_User_Invalid_Id_Path_Variable() throws Exception {
        this.mockMvc.perform(get("/api/user/{id}", "abc"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void find_All_Users() throws Exception {
        this.mockMvc.perform(get("/api/user"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    void get_List_New_Users_By_Reputation() throws Exception {
        this.mockMvc.perform(get("/api/user/new/reputation?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get_List_New_Users_By_Reputation_Request_Parameter_Negative() throws Exception {
        this.mockMvc.perform(get("/api/user/new/reputation?count=5&page=1&weeks=-1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_New_Users_By_Reputation_Request_Parameter_Zero() throws Exception {
        this.mockMvc.perform(get("/api/user/new/reputation?count=0&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_New_Users_By_Reputation_Request_Parameter_Invalid() throws Exception {
        this.mockMvc.perform(get("/api/user/new/reputation?count=abc&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Creation_Date() throws Exception {
        this.mockMvc.perform(get("/api/user/new?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get_List_Users_By_Creation_Date_Request_Parameter_Negative() throws Exception {
        this.mockMvc.perform(get("/api/user/new?count=-1&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Creation_Date_Request_Parameter_Zero() throws Exception {
        this.mockMvc.perform(get("/api/user/new?count=5&page=1&weeks=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Creation_Date_Request_Parameter_Invalid() throws Exception {
        this.mockMvc.perform(get("/api/user/new?count=5&page=abc&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Reputation() throws Exception {
        this.mockMvc.perform(get("/api/user/reputation?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get_List_Users_By_Reputation_Request_Parameter_Negative() throws Exception {
        this.mockMvc.perform(get("/api/user/reputation?count=5&page=-1&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Reputation_Request_Parameter_Zero() throws Exception {
        this.mockMvc.perform(get("/api/user/reputation?count=0&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Reputation_Request_Parameter_Invalid() throws Exception {
        this.mockMvc.perform(get("/api/user/reputation?count=5&page=1&weeks=abc"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Quantity_Edited_Text() throws Exception {
        this.mockMvc.perform(get("/api/user/editor?count=5&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get_List_Users_By_Quantity_Edited_Text_Request_Parameter_Negative() throws Exception {
        this.mockMvc.perform(get("/api/user/editor?count=-1&page=1&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Quantity_Edited_Text_Request_Parameter_Zero() throws Exception {
        this.mockMvc.perform(get("/api/user/editor?count=5&page=1&weeks=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Quantity_Edited_Text_Request_Parameter_Invalid() throws Exception {
        this.mockMvc.perform(get("/api/user/editor?count=5&page=abc&weeks=12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Moderator() throws Exception {
        this.mockMvc.perform(get("/api/user/moderator"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void get_List_Users_By_Name_To_Search() throws Exception {
        this.mockMvc.perform(get("/api/user/find?count=5&page=1&weeks=12&name=Андрей"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void get_List_Users_By_Name_To_Search_Request_Parameter_Negative() throws Exception {
        this.mockMvc.perform(get("/api/user/find?count=5&page=-1&weeks=12&name=Андрей"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Name_To_Search_Request_Parameter_Zero() throws Exception {
        this.mockMvc.perform(get("/api/user/find?count=5&page=1&weeks=0&name=Андрей"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Name_To_Search_Request_Parameter_Invalid() throws Exception {
        this.mockMvc.perform(get("/api/user/find?count=abc&page=1&weeks=12&name=Андрей"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void get_List_Users_By_Name_To_Search_Request_Parameter_Name_Not_String() throws Exception {
        this.mockMvc.perform(get("/api/user/find?count=5&page=1&weeks=12&name=4"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


//    @Test
//    void getListUsersByVoice() throws Exception {
//        this.mockMvc.perform(get("/api/user/voice?count=5&page=1&weeks=12"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

}


//    @Test
//    void getListUsersByNameToSearch() throws Exception {
//        mockMvc.perform(get("/api/user/{count}/page/{page}/name?name=Андрей", 3L, 1L))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$.key").value(""))   // List
//                .andExpect(jsonPath("$.value").value(""));  // count
//                .andExpect(jsonPath("$.value", Matchers.is(2)));
//    }


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

