package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import com.javamentor.qa.platform.service.abstracts.model.UserFavoriteQuestionService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteQuestionServiceImpl extends ReadWriteServiceImpl<UserFavoriteQuestion, Long> implements UserFavoriteQuestionService {

    private final UserFavoriteQuestionDAO userFavoriteQuestionDAO;

    public UserFavoriteQuestionServiceImpl(UserFavoriteQuestionDAO userFavoriteQuestionDAO) {
        super(userFavoriteQuestionDAO);
        this.userFavoriteQuestionDAO = userFavoriteQuestionDAO;
    }
}
