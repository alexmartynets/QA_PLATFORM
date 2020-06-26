package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionDAO;
import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuestionServiceImpl extends ReadWriteServiceImpl<Question, Long> implements QuestionService {

    private final QuestionDAO questionDAO;
    private final TagDAO tagDAO;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, TagDAO tagDAO) {
        super(questionDAO);
        this.questionDAO = questionDAO;
        this.tagDAO = tagDAO;
    }

    @Override
    @Transactional
    public void persist(Question question) {
        question.getTags().forEach(f -> {
            Optional<Long> optionalLong = tagDAO.getTagIdByName(f.getName());
            if (!optionalLong.isPresent()) {
                tagDAO.persist(f);
            } else {
                f.setId(optionalLong.get());
            }
        });
        questionDAO.persist(question);
    }

}
