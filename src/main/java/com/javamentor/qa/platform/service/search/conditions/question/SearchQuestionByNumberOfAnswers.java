package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("answers")
public class SearchQuestionByNumberOfAnswers implements Search {

    private final SearchQuestionDAO searchQuestionDAO;

    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public SearchQuestionByNumberOfAnswers(SearchQuestionDAO searchQuestionDAO, QuestionDtoDao questionDtoDao) {
        this.searchQuestionDAO = searchQuestionDAO;
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public List<QuestionDto> getList(String searchAnswer) {
        if (searchAnswer.chars().allMatch(Character::isDigit)) {
            return searchQuestionDAO.getQuestionsByNumberOfAnswers(Long.parseLong(searchAnswer));
        }
        List<QuestionDto> list = searchQuestionDAO.getQuestionsSortedByVotes();
        list.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        return list;
    }
}