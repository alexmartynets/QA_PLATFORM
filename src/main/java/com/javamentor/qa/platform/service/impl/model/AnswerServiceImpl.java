package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.dao.abstracts.model.QuestionDAO;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerServiceImpl extends ReadWriteServiceImpl<Answer, Long> implements AnswerService {

    private final AnswerDAO answerDAO;
    private final QuestionDAO questionDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AnswerServiceImpl(AnswerDAO answerDAO, QuestionDAO questionDAO) {
        super(answerDAO);
        this.answerDAO = answerDAO;
        this.questionDAO = questionDAO;
    }


    @Override
    public void resetIsHelpful(Long questionId) {
        Optional<Answer> answerOptional = answerDAO.getHelpfulAnswerByQuestionId(questionId);
        if (answerOptional.isPresent()) {
            Answer answer = answerOptional.get();
            answer.setIsHelpful(false);
            answer.setDateAcceptTime(null);
            answerDAO.update(answer);
        }
    }


    @Override
    public Answer updateAnswerHelpful(Long answerId, Long questionId, Long userId, Boolean isHelpful) {
        Answer answer = answerDAO.getByKey(answerId);
        Question question = questionDAO.getByKey(questionId);
        if (answer == null) {
            logger.error(String.format("Answer with id %s does not exist in DB", answerId));
            throw new EntityNotFoundException(String.format("Answer with id %s does not exist in DB", answerId));
        }
        if (question == null) {
            logger.error(String.format("Question with id %s does not exist in DB", questionId));
            throw new EntityNotFoundException(String.format("Question with id %s does not exist in DB", questionId));
        }
        if (!question.getUser().getId().equals(userId)) {
            logger.error(String.format("Question with id %s does not match user with id %s isHelpful cannot be change.", questionId, userId));
            throw new EntityNotFoundException(String.format("Question with id %s does not match user with id %s isHelpful cannot be change.", questionId, userId));
        }
        if (answer.getQuestion().getId().equals(questionId)) {
            resetIsHelpful(answer.getQuestion().getId());
            answer.setIsHelpful(isHelpful);
            if (isHelpful) {
                answer.setDateAcceptTime(LocalDateTime.now());
            }
            update(answer);
            return answer;
        } else {
            logger.error(String.format("Question with id %s does not match answer with id %s", questionId, answerId));
            throw new EntityNotFoundException(String.format("Question with id %s does not match answer with id %s", questionId, answerId));
        }
    }

    @Override
    public Answer updateAnswerBody(Long answerId, Long questionId, Long userId, String htmlBody) {
        Answer answer = answerDAO.getByKey(answerId);
        Question question = questionDAO.getByKey(questionId);
        if (answer == null) {
            logger.error(String.format("Answer with id %s does not exist in DB", answerId));
            throw new EntityNotFoundException(String.format("Answer with id %s does not exist in DB", answerId));
        }
        if (question == null) {
            logger.error(String.format("Question with id %s does not exist in DB", questionId));
            throw new EntityNotFoundException(String.format("Question with id %s does not exist in DB", questionId));
        }
        if (!answer.getQuestion().getId().equals(questionId)) {
            logger.error(String.format("Question with id %s does not match answer with id %s", questionId, answerId));
            throw new EntityNotFoundException(String.format("Question with id %s does not match answer with id %s", questionId, answerId));
        }
        if (answer.getUser().getId().equals(userId)) {
            answer.setHtmlBody(htmlBody);
            update(answer);
            return answer;
        } else {
            logger.error(String.format("Answer with id %s does not match user with id %s", answerId, userId));
            throw new EntityNotFoundException(String.format("Answer with id %s does not match user with id %s", answerId, userId));
        }
    }
}
