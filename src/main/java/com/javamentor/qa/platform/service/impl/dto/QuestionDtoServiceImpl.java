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
    private final QuestionConverter questionConverter;
    private final QuestionService questionService;

    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao, QuestionConverter questionConverter, QuestionService questionService) {
        this.questionDtoDao = questionDtoDao;
        this.questionConverter = questionConverter;
        this.questionService = questionService;
    }

    @Override
    public List<QuestionDto> getAllQuestionDto() {
        return questionDtoDao.getQuestionDtoList();
    }

    @Override
    public Pair<Long, List<QuestionDto>> getPaginationQuestion(int page, int size) {
        List<QuestionDto> list = questionDtoDao.getQuestionList(page, size);
        list.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
        Pair<Long, List<QuestionDto>> result = new Pair<>(questionDtoDao.getCount(), list);
        return result;
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

    @Override
    @Transactional
    public Optional<QuestionDto> toUpdateQuestionDtoTitleOrDescription(QuestionDto questionDtoFromClient) {
        Optional<QuestionDto> dtoById = questionDtoDao.getQuestionDtoById(questionDtoFromClient.getId());
        if (!dtoById.isPresent()) {
            return Optional.empty();
        }
        QuestionDto questionDto = dtoById.get();
        questionDto.setTitle(questionDtoFromClient.getTitle());
        questionDto.setDescription(questionDtoFromClient.getDescription());
        questionService.update(questionConverter.toEntity(questionDto));
        return questionDtoDao.getQuestionDtoById(questionDto.getId());
    }

    @Override
    @Transactional
    public Optional<QuestionDto> toVoteForQuestion(Long id, int vote) {
        Optional<QuestionDto> questionDtoById = getQuestionDtoById(id);
        if (!questionDtoById.isPresent()) {
            return Optional.empty();
        }
        QuestionDto questionDto = questionDtoById.get();
        switch (vote) {
            case 0:
                vote = questionDto.getCountValuable() - 1;
                break;
            case 1:
                vote = questionDto.getCountValuable() + 1;
        }
        questionDto.setCountValuable(vote);
        questionService.update(questionConverter.toEntity(questionDto));
        return questionDtoDao.getQuestionDtoById(id);
    }
}