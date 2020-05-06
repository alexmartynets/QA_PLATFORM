package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.dao.impl.model.UserFavoriteQuestionDAOImpl;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteQuestionService extends UserFavoriteQuestionDAOImpl<UserFavoriteQuestion, Long> {
}
