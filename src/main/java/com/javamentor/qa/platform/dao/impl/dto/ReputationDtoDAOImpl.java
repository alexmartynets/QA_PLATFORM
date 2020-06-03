package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import net.bytebuddy.build.Plugin;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ReputationDtoDAOImpl extends ReadWriteDAOImpl<ReputationDto, Long> implements ReputationDtoDAO {

    @Override
    public Long getCountUsers() {
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r", Long.class)
                .getSingleResult();
    }

    @Override
    public Long getCountUsersByName(String name) {
//        return entityManager.createQuery("SELECT COUNT(u.id) FROM User as u WHERE u.fullName LIKE CONCAT(:searchKeyword, '%')", Long.class)
//                .setParameter("searchKeyword", name.toLowerCase())
//                .getSingleResult();
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReputationDto> getListUsersToPagination(int page, int count) {
        List<ReputationDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)," +
                "SUM(r.voiceCount) " +
                "FROM Reputation r GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return ReputationDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .userId(((Number) objects[1]).longValue())
                                .fullName((String) objects[2])
                                .aboutUser((String) objects[3])
                                .cityUser((String) objects[4])
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
    public List<ReputationDto> getListUsersByNameToSearch(String name, int page, int count) {


        return null;
    }
}

/*               List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "u.id, " +
                "u.fullName, " +
                "u.email, " +
                "u.role.name, " +
                "u.persistDateTime, " +
                "u.reputationCount, " +
                "u.about, " +
                "u.city, " +
                "u.imageUser, " +
                "u.lastUpdateDateTime, " +
                "u.linkGitHub, " +
                "u.linkSite, " +
                "u.linkVk " +
                "FROM User u WHERE u.fullName LIKE CONCAT(:searchKeyword, '%') ORDER BY u.reputationCount DESC")
                .setParameter("searchKeyword", name.toLowerCase())
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return UserDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .fullName((String) objects[1])
                                .email((String) objects[2])
                                .role((String) objects[3])
                                .persistDateTime((LocalDateTime) objects[4])
                                .reputationCount((Integer) objects[5])
                                .about((String) objects[6])
                                .city((String) objects[7])
                                .imageUser((byte[]) objects[8])
                                .lastUpdateDateTime((LocalDateTime) objects[9])
                                .linkGitHub((String) objects[10])
                                .linkSite((String) objects[11])
                                .linkVk((String) objects[12])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }*/
