package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class AnswerServiceImpl extends ReadWriteServiceImpl<Answer, Long> implements AnswerService {

    private final AnswerDAO answerDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AnswerServiceImpl(AnswerDAO answerDAO) {
        super(answerDAO);
        this.answerDAO = answerDAO;
    }


    @Override
    public void resetIsHelpful(Long questionId) {
        Answer answer = answerDAO.getHelpfulAnswerByQuestionId(questionId);
        if (answer != null) {
            answer.setIsHelpful(false);
            answer.setDateAcceptTime(null);
            answerDAO.update(answer);
        }
    }

    @Override
    public Answer updateAnswerCount(Long answerId, Boolean count) {
        Answer answer = getByKey(answerId);
        if(answer != null) {
            if (count) {
                answer.setCountValuable(answer.getCountValuable() + 1);
            } else {
                answer.setCountValuable(answer.getCountValuable() - 1);
            }
            update(answer);
            return answer;
        }else {
            logger.error(String.format("Answer with id %s does not exist in DB", answerId));
            throw new EntityNotFoundException(String.format("Answer with id %s does not exist in DB", answerId));
        }
    }

    @Override
    public Answer updateAnswerHelpful(Long answerId, Boolean isHelpful) {
        Answer answer = getByKey(answerId);
        if(answer != null) {
            resetIsHelpful(answer.getQuestion().getId());
            answer.setIsHelpful(isHelpful);
            if(isHelpful){
                answer.setDateAcceptTime(LocalDateTime.now());
            }
            update(answer);
            return answer;
        }else {
            logger.error(String.format("Answer with id %s does not exist in DB", answerId));
            throw new EntityNotFoundException(String.format("Answer with id %s does not exist in DB", answerId));
        }
    }

    @Override
    public Answer updateAnswerBody(Long answerId, String htmlBody) {
        Answer answer = getByKey(answerId);
        if(answer != null) {
            answer.setHtmlBody(htmlBody);
            update(answer);
            return answer;
        }else {
            logger.error(String.format("Answer with id %s does not exist in DB", answerId));
            throw new EntityNotFoundException(String.format("Answer with id %s does not exist in DB", answerId));
        }

    }
}
