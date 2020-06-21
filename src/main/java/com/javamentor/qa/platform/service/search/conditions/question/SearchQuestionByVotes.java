package com.javamentor.qa.platform.service.search.conditions.question;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("votes")
public class SearchQuestionByVotes implements Search {

    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public SearchQuestionByVotes(SearchQuestionDAO searchQuestionDAO) {
        this.searchQuestionDAO = searchQuestionDAO;
    }

    @Override
    public List<QuestionDto> getList(String searchVotes) {
        if (searchVotes.chars().allMatch(Character::isDigit)) {
            return searchQuestionDAO.getQuestionsByNumberOfVotes(Long.parseLong(searchVotes));
//            return searchQuestionDAO.getQuestionsByNumberOfVotes(Integer.parseInt(searchVotes));
        }
        return searchQuestionDAO.getQuestionsSortedByVotes();
    }
}