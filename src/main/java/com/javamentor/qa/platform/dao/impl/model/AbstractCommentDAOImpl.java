package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class AbstractCommentDAOImpl extends AbstractDAOImpl<Comment, Long> {

    @PersistenceContext
    protected EntityManager entityManager;

    //    список comment к Question
    public List<Comment> getCommentsToQuestion(Long questionId) {
        String hql = "select c from Comment as c join CommentQuestion as cq on c.id = cq.id where cq.question.id = :questionId";
        List<Comment> list = entityManager.createQuery(hql, Comment.class)
                .setParameter("questionId", questionId).getResultList();
        list.forEach(System.out::println);
        return list.isEmpty() ? null : list;
    }

    //    список comment к Answer
    public List<Comment> getCommentsToAnswer(Long answerId) {
        String hql = "select c from Comment as c join CommentAnswer as ca on c.id = ca.id where ca.answer.id = :answerId";
        List<Comment> list = entityManager.createQuery(hql, Comment.class)
                .setParameter("answerId", answerId)
                .getResultList();
        list.forEach(System.out::println);
        return list.isEmpty() ? null : list;
    }
}

/*
* Какая иеархия?
* */
