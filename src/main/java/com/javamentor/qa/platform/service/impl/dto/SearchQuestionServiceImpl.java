package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.SearchService;
import com.javamentor.qa.platform.service.search.main.SearchQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchQuestionServiceImpl implements SearchService {

    private final SearchQuestions searchQuestions;

    private final SearchQuestionDAO searchQuestionDAO;

    @Autowired
    public SearchQuestionServiceImpl(SearchQuestions searchQuestions, SearchQuestionDAO searchQuestionDAO) {
        this.searchQuestions = searchQuestions;
        this.searchQuestionDAO = searchQuestionDAO;
    }

    public List<QuestionDto> search(String searchRequest) {
        List<QuestionDto> list = searchQuestions.getResult(searchRequest);
        for (QuestionDto result : list) {
            QuestionDto questionDto = searchQuestionDAO.settersForCommonSearch(result.getId()).get();
            result.setTags(questionDto.getTags());
            result.setCountAnswer(questionDto.getCountAnswer());
            result.setIsHelpful(questionDto.getIsHelpful());
        }
        return list;
    }
}