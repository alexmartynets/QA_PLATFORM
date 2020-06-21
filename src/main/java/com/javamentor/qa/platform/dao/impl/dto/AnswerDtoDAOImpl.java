package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
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
                        "a.question.id = :questionId order by a.updateDateTime desc")
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
                        "a.question.id = :questionId order by a.countValuable desc")
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
                        "a.question.id = :questionId order by a.persistDateTime asc")
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

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public Boolean isUserAlreadyAnswered(Long questionId, Long userId) {
       List<AnswerDto> answerDto = entityManager
               .createQuery("select a from Answer a where a.question.id = :questionId and a.user.id = :userId")
               .setParameter("questionId", questionId)
               .setParameter("userId", userId)
               .getResultList();
        return answerDto.size() == 0;
    }
}
