package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.webapp.converter.QuestionConverter;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;

    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao) {
        this.questionDtoDao = questionDtoDao;
    }

    @Override
    public List<QuestionDto> getAllQuestionDto() {
        return questionDtoDao.getQuestionDtoList();
    }

    @Override
    public Pair<Long, List<QuestionDto>> getPaginationQuestion(int page, int size) {
        List<QuestionDto> list = questionDtoDao.getQuestionList(page, size);
        list.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        return new Pair<>(questionDtoDao.getCount(), list);
    }

    @Override
    public Optional<QuestionDto> getQuestionDtoById(Long id) {
        return questionDtoDao.getQuestionDtoById(id);
    }

    @Override
    public List<QuestionDto> getQuestionDtoListByUserId(Long userId) {
        return questionDtoDao.getQuestionDtoListByUserId(userId);
    }

    @Override
    public Optional<QuestionDto> hasQuestionAnswer(Long questionId) {
        return questionDtoDao.hasQuestionAnswer(questionId);
    }
}