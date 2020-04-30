package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.impl.RoleServiceTest;
import com.javamentor.qa.platform.service.impl.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {


    @Autowired
    private UserServiceTest userServiceTest;

    @Autowired
    private RoleServiceTest roleServiceTest;

    @GetMapping()
    public ResponseEntity<String> getQuestion(){

        userServiceTest.deleteByKeyCascadeIgnore(5L);
//        User user = new User();
//        user.setFullName("eeee");
//        user.setEmail("uuu@uuu");
//        user.setPassword("pass");
//        user.setRole(roleServiceTest.getByKey(1L));
//        userServiceTest.persist(user);
//        for(User u:userServiceTest.getAll()){
//            System.out.println(u.toString());
//        }
        return ResponseEntity.ok("Тест секьюрити");
    }

}
