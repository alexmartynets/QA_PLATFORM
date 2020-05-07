package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDAO;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDAOImpl extends ReadWriteDAOImpl<Answer, Long> implements AnswerDAO {

}


//@Repository
//public class AnswerDaoImpl extends ReadWriteDaoImpl<Answer, Long> implements AnswerDAO<T, PK> {
//    @Override
//    public List getAnswersByQuestionID(Object questionId) {
//        return null;
//    }
//
//}
