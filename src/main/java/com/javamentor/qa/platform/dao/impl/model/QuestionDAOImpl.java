package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionDAO;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAOImpl extends ReadWriteDAOImpl<Question, Long> implements QuestionDAO {

}
