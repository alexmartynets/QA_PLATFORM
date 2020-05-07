package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class UserFavoriteQuestionDAOImpl
        extends ReadWriteDAOImpl<UserFavoriteQuestion, Long>
        implements UserFavoriteQuestionDAO {
}
