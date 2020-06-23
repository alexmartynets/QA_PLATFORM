package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AnswerDtoDAOImpl implements AnswerDtoDAO {
    private final String HQL = "select " +
            "a.id, " +
            "a.question.id, " +
            "a.htmlBody, " +
            "a.persistDateTime, " +
            "a.dateAcceptTime, " +
            "a.updateDateTime, " +
            "a.countValuable, " +
            "a.isHelpful, " +
            "a.isDeleted, " +
            "a.user.id, " +
            "a.user.fullName, " +
            "a.user.imageUser, " +
            "a.user.reputationCount " +
            "from " +
            "Answer a " +
            "where ";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId) {
        return entityManager
                .createQuery(HQL +
                        "a.question.id = :questionId order by a.updateDateTime desc " +
                        "")
                .setParameter("questionId", questionId)
                .unwrap(Query.class)
                .setResultTransformer(resultTransformer())
                .getResultList();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId) {
        return entityManager
                .createQuery(HQL +
                        "a.question.id = :questionId order by a.countValuable desc" +
                        "")
                .setParameter("questionId", questionId)
                .unwrap(Query.class)
                .setResultTransformer(resultTransformer())
                .getResultList();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId) {
        return entityManager
                .createQuery(HQL +
                        "a.question.id = :questionId order by a.persistDateTime asc" +
                        "")
                .setParameter("questionId", questionId)
                .unwrap(Query.class)
                .setResultTransformer(resultTransformer())
                .getResultList();
    }

    private ResultTransformer resultTransformer() {
        return new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                return getObject(tuple);
            }

            @Override
            public List<AnswerDto> transformList(List list) {
                return list;
            }
        };
    }

    private Object getObject(Object[] tuple) {

        UserDto userDto = UserDto.builder()
                .id(((Number) tuple[9]).longValue())
                .fullName(String.valueOf(tuple[10]))
                .imageUser((byte[]) tuple[11])
                .reputationCount((Integer) tuple[12])
                .build();

        return AnswerDto.builder()
                .id(((Number) tuple[0]).longValue())
                .questionId(((Number) tuple[1]).longValue())
                .htmlBody((String) tuple[2])
                .persistDateTime((LocalDateTime) tuple[3])
                .dateAcceptTime((LocalDateTime) tuple[4])
                .updateDateTime((LocalDateTime) tuple[5])
                .countValuable((Integer) tuple[6])
                .isHelpful((Boolean) tuple[7])
                .isDeleted((Boolean) tuple[8])
                .userDto(userDto)
                .build();
    }

    // new methods
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswerDtoByUserId(Long user_id, String sort, int page) {
        List<AnswerDto> answerList = entityManager.createQuery("SELECT " +
                "a.id, " +
                "a.persistDateTime, " +
                "a.isHelpful, " +
                "a.countValuable, " +
                "a.question.id, " +
                "a.question.title " +
                "FROM Answer a WHERE a.user.id = :user_id " +
                "ORDER BY " + sort + " DESC")
                .setParameter("user_id", user_id)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] aliases) {
                        return AnswerDto.builder()
                                .id((Long) objects[0])
                                .persistDateTime((LocalDateTime) objects[1])
                                .isHelpful((Boolean) objects[2])
                                .countValuable((Integer) objects[3])
                                .questionId((Long) objects[4])
                                .htmlBody((String) objects[5])
                                .build();
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                }).getResultList();
        return answerList.isEmpty() ? Collections.emptyList() : answerList;
    }

    @Override
    public Long getAnswerCountByUserId(long user_id) {
        return (Long) entityManager.createQuery("SELECT COUNT(a) " +
                "FROM Answer a WHERE a.user.id = :user_id")
                .setParameter("user_id", user_id)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TagDto> getTagsFromAnswerByUserId(long user_id)  {
        List<TagDto> list = entityManager.createQuery("SELECT " +
//                "q.id, " +
                "t.id, " +
                "t.name, " +
                "t.description " +
                "FROM Question q " +
                "LEFT JOIN Answer a ON q.id = a.question.id " +
                "JOIN q.tags t " +
                "WHERE a.user.id = :user_id ")
//                " OR a.user.id = :user_id")
                .setParameter("user_id", user_id)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        return TagDto.builder()
                                .id((Long) tuple[0])
                                .name((String) tuple[1])
                                .description((String) tuple[2])
//                                .questionId((Long) tuple[0])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }).getResultList();

        return list.isEmpty()? Collections.emptyList() : list;
    }

}
