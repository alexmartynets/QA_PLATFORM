package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("helpful")
public class SearchQuestionByHelpful implements Search {

    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public SearchQuestionByHelpful(SearchQuestionDAO searchQuestionDAO) {
        this.searchQuestionDAO = searchQuestionDAO;
    }

    @Override
    public List<QuestionDto> getList(String searchHelpful) {
        if (searchHelpful.equals("yes")) {
            return searchQuestionDAO.getQuestionsByFieldHelpfulTrue();
        } else if (searchHelpful.equals("no")) {
            return searchQuestionDAO.getQuestionsByFieldHelpfulFalse();
        } else {
            return searchQuestionDAO.getQuestionsSortedByVotes();
        }
    }
}