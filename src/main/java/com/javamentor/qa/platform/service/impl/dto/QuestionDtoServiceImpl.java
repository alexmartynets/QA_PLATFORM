package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
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
        questionDtoDao.updateQuestionDtoTitleAndDescription(questionDtoFromClient);
        return questionDtoDao.getQuestionDtoById(dtoById.get().getId());
    }

    @Override
    @Transactional
    public Optional<QuestionDto> toVoteForQuestion(Long id, int vote) {
        Optional<QuestionDto> questionDto = getQuestionDtoById(id);
        if (!questionDto.isPresent()) {
            return Optional.empty();
        }
        switch (vote) {
            case 0:
                vote = questionDto.get().getCountValuable() - 1;
                break;
            case 1:
                vote = questionDto.get().getCountValuable() + 1;
        }
        questionDtoDao.toVoteForQuestion(id, vote);
        return questionDtoDao.getQuestionDtoById(id);
    }
}