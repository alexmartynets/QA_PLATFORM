package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.impl.RoleServiceTest;
import com.javamentor.qa.platform.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/question")
public class QuestionResourceController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleServiceTest roleServiceTest;

    @GetMapping()
    public ResponseEntity<String> getQuestion(){

//        userServiceTest.deleteByKeyCascadeIgnore(5L);
        User user = new User();
        user.setFullName("eeee1");
        user.setEmail("uuu@uuu1");
        user.setPassword("pass1");
        user.setRole(roleServiceTest.getByKey(1L));
        userService.persist(user);
        for(User u: userService.getAll()){
            System.out.println(u.toString());
        }
        return ResponseEntity.ok("Тест секьюрити QuestionResourceController");
    }

}
