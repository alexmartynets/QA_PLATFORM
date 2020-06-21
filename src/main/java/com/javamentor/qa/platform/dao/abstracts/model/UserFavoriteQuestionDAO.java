package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;

import java.util.List;

public interface UserFavoriteQuestionDAO extends ReadWriteDAO<UserFavoriteQuestion, Long>{
    List<QuestionDto> getUserFavorite(Long user_id, String sort, int page);
}
