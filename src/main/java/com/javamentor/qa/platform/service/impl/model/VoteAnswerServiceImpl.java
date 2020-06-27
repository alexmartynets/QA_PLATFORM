package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDAO;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteAnswerServiceImpl extends ReadWriteServiceImpl<VoteAnswer, VoteAnswer.VoteAnswerPK> implements VoteAnswerService {
    private final VoteAnswerDAO voteAnswerDAO;

    @Autowired
    public VoteAnswerServiceImpl(VoteAnswerDAO voteAnswerDAO) {
        super(voteAnswerDAO);
        this.voteAnswerDAO = voteAnswerDAO;
    }
}
