package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final UserDtoService userDtoService;

    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao, UserDtoService userDtoService) {
        this.questionDtoDao = questionDtoDao;
        this.userDtoService = userDtoService;
    }

    @Override
    public List<QuestionDto> getAllQuestionDto() {
        List<QuestionDto> questionDtoList = questionDtoDao.getQuestionDtoList();
        questionDtoList
                .forEach(x -> x.setUserDto(userDtoService.getUserDtoById(x.getUserDto().getId()).get()));
        return questionDtoList;
    }

    @Override
    public Optional<QuestionDto> getQuestionDtoById(Long id) {
        Optional<QuestionDto> questionDto = questionDtoDao.getQuestionDtoById(id);
        if (questionDto.isPresent()) {
            setUserDtoInQuestionDto(questionDto);
        }
        return questionDto;
    }

    @Override
    public List<QuestionDto> getQuestionDtoListByUserId(Long userId) {
        return questionDtoDao.getQuestionDtoListByUserId(userId);
    }

    private void setUserDtoInQuestionDto(Optional<QuestionDto> questionDto) {
        if (questionDto.isPresent()) {
            Optional<UserDto> userDto = userDtoService.getUserDtoById(questionDto.get().getUserDto().getId());
            userDto.ifPresent(dto -> questionDto.get().setUserDto(dto));
        }
    }
}