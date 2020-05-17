package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerDtoDaoImpl implements AnswerDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerDto> getAnswersDtoByQuestionId(Long questionId) {
        return entityManager
                .createQuery("select " +
                        "a.id, " +
                        "a.htmlBody, " +
                        "a.persistDateTime, " +
                        "a.countValuable, " +
                        "a.isHelpful, " +
                        "a.user.fullName, " +
                        "a.user.imageUser, " +
                        "a.user.reputationCount, " +
                        "a.question.id, " +
                        "a.user.id " +
                        "from " +
                        "Answer a " +
                        "where " +
                        "a.question.id = :questionId" +
                        "")
                .setParameter("questionId", questionId)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        return getObject(tuple);
                    }

                    @Override
                    public List<AnswerDto> transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
    }

    @Override
    public AnswerDto getAnswerDtoById(Long answerId) {
        return (AnswerDto) entityManager
                .createQuery("select " +
                        "a.id, " +
                        "a.htmlBody, " +
                        "a.persistDateTime, " +
                        "a.countValuable, " +
                        "a.isHelpful, " +
                        "a.user.fullName, " +
                        "a.user.imageUser, " +
                        "a.user.reputationCount, " +
                        "a.question.id, " +
                        "a.user.id " +
                        "from " +
                        "Answer a " +
                        "where " +
                        "a.id = :answerId" +
                        "")
                .setParameter("answerId", answerId)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        return getObject(tuple);
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getSingleResult();
    }
    private Object getObject (Object[] tuple){

        UserDto userDto = UserDto.builder()
                .fullName(String.valueOf(tuple[5]))
                .imageUser((byte[]) tuple[6])
                .reputationCount((Integer) tuple[7])
                .id(((Number)tuple[9]).longValue())
                .build();

        return AnswerDto.builder()
                .id(((Number) tuple[0]).longValue())
                .htmlBody((String) tuple[1])
                .persistDateTime((LocalDateTime) tuple[2])
                .countValuable((Integer) tuple[3])
                .isHelpful((Boolean) tuple[4])
                .userDto(userDto)
                .questionId(((Number) tuple[8]).longValue())
                .build();
    }
}
