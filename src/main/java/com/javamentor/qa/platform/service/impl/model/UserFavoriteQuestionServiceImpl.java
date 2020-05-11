package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import com.javamentor.qa.platform.service.abstracts.model.UserFavoriteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteQuestionServiceImpl extends ReadWriteServiceImpl<UserFavoriteQuestion, Long> implements UserFavoriteQuestionService {

    private final UserFavoriteQuestionDAO userFavoriteQuestionDAO;

    @Autowired
    public UserFavoriteQuestionServiceImpl(UserFavoriteQuestionDAO userFavoriteQuestionDAO) {
        super(userFavoriteQuestionDAO);
        this.userFavoriteQuestionDAO = userFavoriteQuestionDAO;
    }
}
