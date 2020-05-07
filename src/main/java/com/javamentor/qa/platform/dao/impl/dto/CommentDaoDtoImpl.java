package com.javamentor.qa.platform.dao.impl.dto;


import com.javamentor.qa.platform.dao.abstracts.dto.CommentDaoDto;
import com.javamentor.qa.platform.models.dto.CommentDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDaoDtoImpl implements CommentDaoDto {

    @PersistenceContext
    protected EntityManager entityManager;

    //    список comment к Question
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        String hql = "select new com.javamentor.qa.platform.models.dto.CommentDto(" +
                "c.id, " +
                "c.text, " +
                "c.commentType," +
                "c.persistDateTime," +
                "c.lastUpdateDateTime, " +
                "c.user.id) " +
                "from Comment as c join CommentQuestion as cq on c.id = cq.id where cq.question.id = :questionId";
        List<CommentDto> list = entityManager.createQuery(hql, CommentDto.class)
                .setParameter("questionId", questionId)
                .getResultList();
        list.forEach(System.out::println);
        return list.isEmpty() ? null : list;
    }

    //    список comment к Answer
    public List<CommentDto> getCommentsToAnswer(Long answerId) {
        String hql = "select new com.javamentor.qa.platform.models.dto.CommentDto(" +
                "c.id, " +
                "c.text, " +
                "c.commentType," +
                "c.persistDateTime," +
                "c.lastUpdateDateTime, " +
                "c.user.id) " +
                "from Comment as c join CommentAnswer as ca on c.id = ca.id where ca.answer.id = :answerId";
        List<CommentDto> list = entityManager.createQuery(hql, CommentDto.class)
                .setParameter("answerId", answerId)
                .getResultList();
        list.forEach(System.out::println);
        return list.isEmpty() ? null : list;
    }
}
