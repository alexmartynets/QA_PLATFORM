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

    @GetMapping
    public ResponseEntity<String> getQuestion() {
        return ResponseEntity.ok("Тест секьюрити");
    }

}
