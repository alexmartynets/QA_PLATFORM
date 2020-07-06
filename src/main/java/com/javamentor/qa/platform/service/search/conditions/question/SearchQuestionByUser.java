package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("user")
public class SearchQuestionByUser implements Search {

    private final UserService userService;

    private final SearchQuestionDAO searchQuestionDAO;

    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public SearchQuestionByUser(UserService userService, SearchQuestionDAO searchQuestionDAO, QuestionDtoDao questionDtoDao) {
        this.userService = userService;
        this.searchQuestionDAO = searchQuestionDAO;
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public List<QuestionDto> getList(String searchUser) {
        if (searchUser.chars().allMatch(Character::isDigit)) {
            Long id = Long.parseLong(searchUser);
            if (userService.existsById(id)) {
                return searchQuestionDAO.getQuestionsByUserId(id);
            }
        }
        List<QuestionDto> list = searchQuestionDAO.getQuestionsSortedByVotes();
        list.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        return list;
    }
}