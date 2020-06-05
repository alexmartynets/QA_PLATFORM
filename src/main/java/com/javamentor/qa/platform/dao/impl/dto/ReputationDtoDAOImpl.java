package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class ReputationDtoDAOImpl extends ReadWriteDAOImpl<ReputationDto, Long> implements ReputationDtoDAO {

    @Override
    public Long getCountUsers(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r " +
                "WHERE r.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
    }

    @Override
    public Long getCountUsersByName(String name, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r " +
                "WHERE r.persistDateTime > :data AND r.user.fullName LIKE CONCAT(:searchKeyword, '%')", Long.class)
                .setParameter("data", data)
                .setParameter("searchKeyword", name.toLowerCase())
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReputationDto> getListUsersByReputationToPagination(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<ReputationDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)," +
                "SUM(r.voiceCount) " +
                "FROM Reputation r WHERE r.persistDateTime > :data " +
                "GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return ReputationDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .reputationId(((Number) objects[1]).longValue())
                                .fullName((String) objects[2])
                                .about((String) objects[3])
                                .city((String) objects[4])
                                .imageUser((byte[]) objects[5])
                                .reputationCount(((Number) objects[6]).longValue())
                                .voiceCount(((Number) objects[7]).longValue())
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReputationDto> getListUsersByVoiceToPagination(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<ReputationDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)," +
                "SUM(r.voiceCount) " +
                "FROM Reputation r WHERE r.persistDateTime > :data " +
                "GROUP BY r.user.id ORDER BY SUM(r.voiceCount) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return ReputationDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .reputationId(((Number) objects[1]).longValue())
                                .fullName((String) objects[2])
                                .about((String) objects[3])
                                .city((String) objects[4])
                                .imageUser((byte[]) objects[5])
                                .reputationCount(((Number) objects[6]).longValue())
                                .voiceCount(((Number) objects[7]).longValue())
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReputationDto> getListUsersByNameToSearch(String name, int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<ReputationDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)," +
                "SUM(r.voiceCount) " +
                "FROM Reputation r WHERE r.persistDateTime > :data AND r.user.fullName LIKE CONCAT(:searchKeyword, '%') " +
                "GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setParameter("data", data)
                .setParameter("searchKeyword", name.toLowerCase())
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return ReputationDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .reputationId(((Number) objects[1]).longValue())
                                .fullName((String) objects[2])
                                .about((String) objects[3])
                                .city((String) objects[4])
                                .imageUser((byte[]) objects[5])
                                .reputationCount(((Number) objects[6]).longValue())
                                .voiceCount(((Number) objects[7]).longValue())
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }
}

