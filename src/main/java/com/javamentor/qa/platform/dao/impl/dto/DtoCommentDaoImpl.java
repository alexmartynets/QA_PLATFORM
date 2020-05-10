package com.javamentor.qa.platform.dao.impl.dto;


import com.javamentor.qa.platform.dao.abstracts.dto.DtoCommentDao;
import com.javamentor.qa.platform.models.dto.CommentDto;
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
public class DtoCommentDaoImpl implements DtoCommentDao {

    @PersistenceContext
    protected EntityManager entityManager;

    //  получить список comment к Question
    public List<CommentDto> getCommentsToQuestion(Long questionId) {
        String hql = "select " +
                "c.id, " +
                "c.text, " +
                "c.commentType," +
                "c.persistDateTime," +
                "c.lastUpdateDateTime, " +
                "c.user.id, " +
                "c.user.fullName " +
                "from Comment as c join CommentQuestion as cq on c.id = cq.id where cq.question.id = :questionId";
        List<CommentDto> list = (List<CommentDto>) entityManager.createQuery(hql)
                .setParameter("questionId", questionId)
                .unwrap(org.hibernate.query.Query.class)
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
                })
                .getResultList();
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    //  получить список comment к Answer
    public List<CommentDto> getCommentsToAnswer(Long answerId) {
        String hql = "select " +
                "c.id, " +
                "c.text, " +
                "c.commentType," +
                "c.persistDateTime," +
                "c.lastUpdateDateTime, " +
                "c.user.id, " +
                "c.user.fullName " +
                "from Comment as c join CommentAnswer as ca on c.id = ca.id where ca.answer.id = :answerId";
        List<CommentDto> list = (List<CommentDto>) entityManager.createQuery(hql)
                .unwrap(Query.class)
                .setParameter("answerId", answerId)
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
                        list.forEach(System.out::println);
                        return list;
                    }
                })
                .getResultList();
        return list.isEmpty() ? Collections.emptyList() : list;
    }
}

