package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentQuestionDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class CommentQuestionDtoDAOImpl extends ReadWriteDAOImpl<Comment, Long> implements CommentQuestionDtoDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        String hql = "SELECT " +
                "c.id, " +
                "c.text, " +
                "c.commentType," +
                "c.persistDateTime," +
                "c.lastUpdateDateTime, " +
                "c.user.id, " +
                "c.user.fullName " +
                "FROM Comment as c JOIN CommentQuestion as cq ON c.id = cq.id WHERE cq.question.id = :questionId";
        List<CommentDto> list = entityManager.createQuery(hql)
                .setParameter("questionId", questionId)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return CommentDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .text((String) objects[1])
                                .commentType((CommentType) objects[2])
                                .persistDateTime((LocalDateTime) objects[3])
                                .lastUpdateDateTime((LocalDateTime) objects[4])
                                .userId(((Number) objects[5]).longValue())
                                .fullName((String) objects[6])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }).getResultList();

        return list.isEmpty() ? Collections.emptyList() : list;
    }

    @Override
    public int hasUserCommentQuestion(Long questionId, Long userId) {
        Number a = (Long) entityManager.createQuery("SELECT " +
                "COUNT (c) FROM Comment AS c JOIN CommentQuestion AS sq ON c.id = sq.id " +
                "WHERE c.user.id = :userId AND sq.question.id = :questionId")
                .unwrap(Query.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .getSingleResult();
        return a == null ? 0 : a.intValue();
    }
}
