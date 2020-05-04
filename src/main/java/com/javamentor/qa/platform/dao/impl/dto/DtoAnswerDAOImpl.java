//package com.javamentor.qa.platform.dao.impl.dto;
//
//import com.javamentor.qa.platform.dao.abstracrt.dto.DtoAnswerDAO;
//import com.javamentor.qa.platform.models.dto.AnswerDto;
//import com.javamentor.qa.platform.models.entity.question.answer.Answer;
//import com.javamentor.qa.platform.webapp.converter.AnswerConverter;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//public abstract class DtoAnswerDAOImpl implements DtoAnswerDAO {
//
//    private AnswerConverter answerConverter;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<AnswerDto> getAnswersDto(Long questionId) {
//        return answerConverter.answersToDTOs(entityManager.createQuery("select a from Answer a where a.question = :questionId", Answer.class)
//                .setParameter("questionId", questionId)
//                .getResultList());
//    }
//}
