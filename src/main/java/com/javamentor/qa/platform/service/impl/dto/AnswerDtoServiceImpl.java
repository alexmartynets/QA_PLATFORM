package com.javamentor.qa.platform.service.impl.dto;


import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerDtoServiceImpl implements AnswerDtoService {

    private final AnswerDtoDAO answerDtoDao;

    @Autowired
    public AnswerDtoServiceImpl(AnswerDtoDAO answerDtoDao) {
        this.answerDtoDao = answerDtoDao;
    }

    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId) {
        return answerDtoDao.getAnswersDtoByQuestionIdSortNew(questionId);
    }

    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId) {
        return answerDtoDao.getAnswersDtoByQuestionIdSortCount(questionId);
    }

    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId) {
        return answerDtoDao.getAnswersDtoByQuestionIdSortDate(questionId);
    }

    @Override
    public Boolean isUserAlreadyAnswered(Long questionId, Long userId) {
        return answerDtoDao.isUserAlreadyAnswered(questionId, userId);
    }
}
