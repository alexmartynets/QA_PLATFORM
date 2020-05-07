package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracrt.model.CommentDAO;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDAOImpl extends AbstractDAOImpl<Comment, Long>{

//    @PersistenceContext
//    protected EntityManager entityManager;
//
//    //    список comment к Question
//    @Override
//    public List<CommentDto> getCommentsToQuestion(Long questionId) {
//        String hql = "select new com.javamentor.qa.platform.models.dto.CommentDto(" +
//                "c.id, " +
//                "c.text, " +
//                "c.commentType," +
//                "c.persistDateTime," +
//                "c.lastUpdateDateTime, " +
//                "c.user.fullName) " +
//                "from Comment as c join CommentQuestion as cq on c.id = cq.id where cq.question.id = :questionId";
//        List<CommentDto> list = entityManager.createQuery(hql, CommentDto.class)
//                .setParameter("questionId", questionId)
//                .getResultList();
//        list.forEach(System.out::println);
//        return list.isEmpty() ? null : list;
//    }
//
//    //    список comment к Answer
//    @Override
//    public List<CommentDto> getCommentsToAnswer(Long answerId) {
//        String hql = "select new com.javamentor.qa.platform.models.dto.CommentDto(" +
//                "c.id, " +
//                "c.text, " +
//                "c.commentType," +
//                "c.persistDateTime," +
//                "c.lastUpdateDateTime, " +
//                "c.user.fullName) " +
//                "from Comment as c join CommentAnswer as ca on c.id = ca.id where ca.answer.id = :answerId";
//        List<CommentDto> list = entityManager.createQuery(hql, CommentDto.class)
//                .setParameter("answerId", answerId)
//                .getResultList();
//        list.forEach(System.out::println);
//        return list.isEmpty() ? null : list;
//    }
}

/*String hql = "select c from Comment as c join CommentAnswer as ca on c.id = ca.id where ca.answer.id = :answerId";*/
