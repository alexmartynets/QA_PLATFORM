package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
            "sum (v.vote), " +
            "a.isHelpful, " +
            "a.isDeleted, " +
            "a.user.id, " +
            "a.user.fullName, " +
            "a.user.imageUser, " +
            "a.user.reputationCount, " +
            "(select (sum(uv.vote)) from AnswerVote uv where uv.voteAnswerPK.user.id = :userId and uv.voteAnswerPK.answer.id = a.id )" +
            "from " +
            "Answer a left join AnswerVote v on a.id = v.voteAnswerPK.answer.id ";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortNew(Long questionId, Long userId) {
        return entityManager
                .createQuery(HQL + "where a.question.id = :questionId group by a.id order by a.updateDateTime desc")
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .unwrap(Query.class)
                .setResultTransformer(resultTransformer())
                .getResultList();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortCount(Long questionId, Long userId) {
        return entityManager
                .createQuery(HQL + "where a.question.id = :questionId group by a.id order by sum (v.vote) desc ")
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .unwrap(Query.class)
                .setResultTransformer(resultTransformer())
                .getResultList();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionIdSortDate(Long questionId, Long userId) {
        return entityManager
                .createQuery(HQL + "where a.question.id = :questionId group by a.id order by a.persistDateTime asc")
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
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
                .countValuable(tuple[6] != null ? ((Number) tuple[6]).intValue() : 0)
                .isHelpful((Boolean) tuple[7])
                .isDeleted((Boolean) tuple[8])
                .userDto(userDto)
                .voteOfUser(tuple[13] == null ? 0 : ((Number) tuple[13]).intValue())
                .build();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public Boolean isUserNotAnswered(Long questionId, Long userId) {
        List<AnswerDto> answerDto = entityManager
                .createQuery("select a from Answer a where a.question.id = :questionId and a.user.id = :userId")
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .getResultList();
        return answerDto.size() == 0;
    }

    // new methods
    @Override
    public Long getAnswerCountByUserId(Long useId) {
        return (Long) entityManager.createQuery("SELECT COUNT(a) " +
                "FROM Answer a WHERE a.user.id = :userId")
                .setParameter("userId", useId)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswerDtoByUserIdSortByDate(Long userId, Integer page) {
        List<AnswerDto> answerList = entityManager.createQuery("SELECT " +
                "a.id as id, " +
                "a.persistDateTime as persistDateTime, " +
                "a.isHelpful as isHelpful, " +
                "a.countValuable as countValuable, " +
                "a.question.id as questionId, " +
                "a.question.title as htmlBody " +
                "FROM Answer a WHERE a.user.id = :userId " +
                "ORDER BY a.persistDateTime DESC")
                .setParameter("userId", userId)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(AnswerDto.class))
                .getResultList();
        return answerList.isEmpty() ? Collections.emptyList() : answerList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswerDtoByUserIdSortByViews(Long userId, Integer page) {
        List<AnswerDto> answerList = entityManager.createQuery("SELECT " +
                "a.id as id, " +
                "a.persistDateTime as persistDateTime, " +
                "a.isHelpful as isHelpful, " +
                "a.countValuable as countValuable, " +
                "a.question.id as questionId, " +
                "a.question.title as htmlBody, " +
                "a.question.viewCount " +
                "FROM Answer a WHERE a.user.id = :userId " +
                "ORDER BY a.question.viewCount DESC")
                .setParameter("userId", userId)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(AnswerDto.class))
                .getResultList();
        return answerList.isEmpty() ? Collections.emptyList() : answerList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswerDtoByUserIdSortByVotes(Long userId, Integer page) {
        List<AnswerDto> answerList = entityManager.createQuery("SELECT " +
                "a.id as id, " +
                "a.persistDateTime as persistDateTime, " +
                "a.isHelpful as isHelpful, " +
                "a.countValuable as countValuable, " +
                "a.question.id as questionId, " +
                "a.question.title as htmlBody " +
                "FROM Answer a WHERE a.user.id = :userId " +
                "ORDER BY a.countValuable DESC")
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .setParameter("userId", userId)
                .unwrap(Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(AnswerDto.class))
                .getResultList();
        return answerList.isEmpty() ? Collections.emptyList() : answerList;
    }

}
