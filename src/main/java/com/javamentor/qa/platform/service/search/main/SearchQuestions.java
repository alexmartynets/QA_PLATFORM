package com.javamentor.qa.platform.service.search.main;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import com.javamentor.qa.platform.service.search.abstracts.SearchPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchQuestions {

    private final List<SearchPattern> searchPatterns;

    private final Map<String, Search> map;

    @Autowired
    public SearchQuestions(List<SearchPattern> searchPatterns, Map<String, Search> map) {
        this.searchPatterns = searchPatterns;
        this.map = map;
    }

    @SuppressWarnings("unchecked")
    public List<QuestionDto> getResult(String searchRequest) {
        for (SearchPattern pattern : searchPatterns){
            if (pattern.findPattern(searchRequest)){
                return (List<QuestionDto>) pattern.result(searchRequest, map);
            }
        }
        return (List<QuestionDto>) map.get("common").getList(searchRequest);
    }
}