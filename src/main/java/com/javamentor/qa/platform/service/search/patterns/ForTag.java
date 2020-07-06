package com.javamentor.qa.platform.service.search.patterns;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.search.abstracts.Search;
import com.javamentor.qa.platform.service.search.abstracts.SearchPattern;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ForTag implements SearchPattern {

    @Override
    public Boolean findPattern(String searchRequest) {
        return searchRequest.startsWith("[") && searchRequest.endsWith("]");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<QuestionDto> result(String searchRequest, Map<String, Search> map) {
        return (List<QuestionDto>) map.get("tag").getList(searchRequest);
    }
}