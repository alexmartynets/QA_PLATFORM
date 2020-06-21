package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.dao.abstracts.model.AnswerVoteDAO;
import com.javamentor.qa.platform.dao.abstracts.model.QuestionDAO;
import com.javamentor.qa.platform.dao.abstracts.model.UserDAO;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerVotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AnswerVotesServiceImpl extends ReadWriteServiceImpl<AnswerVote, Long> implements AnswerVotesService {

    private final AnswerDAO answerDAO;
    private final QuestionDAO questionDAO;
    private final AnswerVoteDAO answerVotesDAO;
    private final UserDAO userDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AnswerVotesServiceImpl(AnswerDAO answerDAO, QuestionDAO questionDAO, AnswerVoteDAO answerVotesDAO, UserDAO userDAO) {
        super(answerVotesDAO);
        this.answerDAO = answerDAO;
        this.questionDAO = questionDAO;
        this.answerVotesDAO = answerVotesDAO;
        this.userDAO = userDAO;
    }


    @Override
    public void addAnswerVote(Long answerId, Long userId, Boolean count) {
        Answer answer = answerDAO.getByKey(answerId);
        User user = userDAO.getByKey(userId);
        if(answer == null) {
            logger.info(String.format("Answer id %d does not exist in DB.", answerId));
            throw new EntityNotFoundException(String.format("Answer id %d does not exist in DB.", answerId));
        }
        if(user == null) {
            logger.info(String.format("User id %d does not exist in DB.", userId));
            throw new EntityNotFoundException(String.format("User id %d does not exist in DB.", userId));
        }
        AnswerVote answerVote = AnswerVote.builder()
                .answer(answer)
                .user(user)
                .build();
        if (count) {
            answerVote.setVote(1);
        } else {
            answerVote.setVote(-1);
        }
        answerVotesDAO.persist(answerVote);
    }

    @Override
    public Integer getVoteOfUserByAnswer(Long answerId, Long questionId, Long userId) {
        if(questionDAO.getByKey(questionId)==null){
            logger.info(String.format("Question id %d does not exist in DB.", questionId));
            throw new EntityNotFoundException(String.format("Question id %d does not exist in DB.", questionId));
        }
        List<AnswerVote> votes = answerVotesDAO.getVotesOfUserByAnswer(answerId, userId);
        int userVote = 0;
        for (AnswerVote vote : votes) {
            userVote += vote.getVote();
        }
        return userVote;
    }

    @Override
    public Integer getVotesOfAnswer(Long answerId) {
        List<AnswerVote> votes = answerVotesDAO.getAllVotesByAnswerId(answerId);
        int answerVotes = 0;
        for (AnswerVote vote : votes) {
            answerVotes += vote.getVote();
        }
        return answerVotes;
    }
}
