package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("tag")
public class SearchQuestionByTag implements Search {

    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public SearchQuestionByTag(SearchQuestionDAO searchQuestionDAO) {
        this.searchQuestionDAO = searchQuestionDAO;
    }


    @Override
    public List<QuestionDto> getList(String searchTag) {
        return searchQuestionDAO.getQuestionsByTag(searchTag.substring(1, searchTag.length() - 1));
    }
}