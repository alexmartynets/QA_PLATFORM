package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDAO;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import org.springframework.stereotype.Repository;

@Repository
public class VoteAnswerDAOImpl extends ReadWriteDAOImpl<VoteAnswer, VoteAnswer.VoteAnswerPK> implements VoteAnswerDAO {
}
