package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("helpful")
public class SearchQuestionByHelpful implements Search {

    private final SearchQuestionDAO searchQuestionDAO;

    private final QuestionDtoDao questionDtoDao;

    @Autowired
    public SearchQuestionByHelpful(SearchQuestionDAO searchQuestionDAO, QuestionDtoDao questionDtoDao) {
        this.searchQuestionDAO = searchQuestionDAO;
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public List<QuestionDto> getList(String searchHelpful) {
        if (searchHelpful.equals("yes")) {
            return searchQuestionDAO.getQuestionsByFieldHelpfulTrue();
        } else if (searchHelpful.equals("no")) {
            return searchQuestionDAO.getQuestionsByFieldHelpfulFalse();
        } else {
            List<QuestionDto> list = searchQuestionDAO.getQuestionsSortedByVotes();
            list.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
            return list;
        }
    }
}