package com.javamentor.qa.platform.service.search.patterns;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import com.javamentor.qa.platform.service.search.abstracts.SearchPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TwoPoint implements SearchPattern {

    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public TwoPoint(SearchQuestionDAO searchQuestionDAO) {
        this.searchQuestionDAO = searchQuestionDAO;
    }

    @Override
    public Boolean findPattern(String searchRequest) {
        return searchRequest.contains(":");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<QuestionDto> result(String searchRequest, Map<String, Search> map) {
        String[] split = searchRequest.split(":");
        if (map.containsKey(split[0])) {
            return (List<QuestionDto>) map.get(split[0]).getList(split[1]);
        }
        return searchQuestionDAO.getQuestionsSortedByVotes();
    }
}