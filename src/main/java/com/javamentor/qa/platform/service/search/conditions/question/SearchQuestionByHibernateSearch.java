package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("common")
public class SearchQuestionByHibernateSearch implements Search {

    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public SearchQuestionByHibernateSearch(SearchQuestionDAO searchQuestionDAO) {
        this.searchQuestionDAO = searchQuestionDAO;
    }

    @Override
    public List<QuestionDto> getList(String searchVariable) {
        if (searchQuestionDAO.getTagByName(searchVariable)) {
            return searchQuestionDAO.getQuestionsByTag(searchVariable);
        }
        return searchQuestionDAO.search(searchVariable);
    }
}