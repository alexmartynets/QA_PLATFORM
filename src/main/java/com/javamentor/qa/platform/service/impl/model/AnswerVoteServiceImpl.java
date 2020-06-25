package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.dao.abstracts.model.AnswerVoteDAO;
import com.javamentor.qa.platform.dao.abstracts.model.QuestionDAO;
import com.javamentor.qa.platform.dao.abstracts.model.UserDAO;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerVoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class AnswerVoteServiceImpl extends ReadWriteServiceImpl<AnswerVote, Long> implements AnswerVoteService {

    private final AnswerDAO answerDAO;
    private final QuestionDAO questionDAO;
    private final AnswerVoteDAO answerVoteDAO;
    private final UserDAO userDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AnswerVoteServiceImpl(AnswerDAO answerDAO, QuestionDAO questionDAO, AnswerVoteDAO answerVoteDAO, UserDAO userDAO) {
        super(answerVoteDAO);
        this.answerDAO = answerDAO;
        this.questionDAO = questionDAO;
        this.answerVoteDAO = answerVoteDAO;
        this.userDAO = userDAO;
    }


    @Override
    public Boolean addAnswerVote(Long questionId, Long answerId, Long userId, Boolean count) {
        Answer answer = answerDAO.getByKey(answerId);
        User user = userDAO.getByKey(userId);
        Question question = questionDAO.getByKey(questionId);
        if (question == null) {
            logger.info(String.format("Question id %d does not exist in DB.", questionId));
            throw new EntityNotFoundException(String.format("Question id %d does not exist in DB.", questionId));
        }
        if (answer == null) {
            logger.info(String.format("Answer id %d does not exist in DB.", answerId));
            throw new EntityNotFoundException(String.format("Answer id %d does not exist in DB.", answerId));
        }
        if (user == null) {
            logger.info(String.format("User id %d does not exist in DB.", userId));
            throw new EntityNotFoundException(String.format("User id %d does not exist in DB.", userId));
        }
        if (!answer.getQuestion().getId().equals(questionId)) {
            logger.info(String.format("Answer id %d does not match question id %d.", answerId, questionId));
            throw new EntityNotFoundException(String.format("Answer id %d does not match question id %d.", answerId, questionId));
        }
        if ((answerVoteDAO.getVotesOfUserByAnswer(answerId, userId) == 0) && !answer.getUser().getId().equals(userId)) {
            AnswerVote answerVote = AnswerVote.builder()
                    .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                            .answer(answer)
                            .user(user)
                            .persistDateTime(LocalDateTime.now())
                            .build())
                    .build();
            if (count) {
                answerVote.setVote(1);
            } else {
                answerVote.setVote(-1);
            }
            answerVoteDAO.persist(answerVote);
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public Boolean getVoteOfUserByAnswer(Long answerId, Long questionId, Long userId) {
//        Answer answer = answerDAO.getByKey(answerId);
//        User user = userDAO.getByKey(userId);
//        Question question = questionDAO.getByKey(questionId);
//        if (question == null) {
//            logger.info(String.format("Question id %d does not exist in DB.", questionId));
//            throw new EntityNotFoundException(String.format("Question id %d does not exist in DB.", questionId));
//        }
//        if (answer == null) {
//            logger.info(String.format("Answer id %d does not exist in DB.", answerId));
//            throw new EntityNotFoundException(String.format("Answer id %d does not exist in DB.", answerId));
//        }
//        if (user == null) {
//            logger.info(String.format("User id %d does not exist in DB.", userId));
//            throw new EntityNotFoundException(String.format("User id %d does not exist in DB.", userId));
//        }
//        return answerVoteDAO.getVotesOfUserByAnswer(answerId, userId) == 0;
//    }

    @Override
    public Integer getVotesOfAnswer(Long answerId) {
        return answerVoteDAO.getAllVotesByAnswerId(answerId);
    }
}
