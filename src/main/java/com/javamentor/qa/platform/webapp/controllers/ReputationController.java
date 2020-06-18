package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.UserBadgesService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rep")
public class ReputationController {

    private final UserService userService;
    private final ReputationService reputationService;
    private final UserBadgesService userBadgesService;

    @Autowired
    public ReputationController(ReputationService reputationService,
                                UserBadgesService userBadgesService,
                                UserService userService) {
        this.reputationService = reputationService;
        this.userBadgesService = userBadgesService;
        this.userService = userService;
    }

    @GetMapping
    public void addReputationForUser(@RequestParam Long user_id,
                                    @RequestParam Integer add_rep) {
        User user = userService.getByKey(user_id);
        reputationService.updateOrInsert(user, add_rep);
//        userBadgesService.checkAndUpdateUserBadges(reputation);
    }
}
