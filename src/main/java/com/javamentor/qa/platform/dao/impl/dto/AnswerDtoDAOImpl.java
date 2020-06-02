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
            "a.countValuable, " +
            "a.isHelpful, " +
            "a.deleted, " +
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
    public List<AnswerDto> getAnswersDtoByQuestionId(Long questionId) {
        return entityManager
                .createQuery(HQL +
                        "a.question.id = :questionId order by a.updateDateTime desc, a.persistDateTime desc " +
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
                .id(((Number) tuple[8]).longValue())
                .fullName(String.valueOf(tuple[9]))
                .imageUser((byte[]) tuple[10])
                .reputationCount((Integer) tuple[11])
                .build();

        return AnswerDto.builder()
                .id(((Number) tuple[0]).longValue())
                .questionId(((Number) tuple[1]).longValue())
                .htmlBody((String) tuple[2])
                .persistDateTime((LocalDateTime) tuple[3])
                .dateAcceptTime((LocalDateTime) tuple[4])
                .countValuable((Integer) tuple[5])
                .isHelpful((Boolean) tuple[6])
                .isDeleted((Boolean) tuple[7])
                .userDto(userDto)
                .build();
    }
}
