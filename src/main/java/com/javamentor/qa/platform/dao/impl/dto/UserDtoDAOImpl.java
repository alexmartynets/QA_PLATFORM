package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDtoDAOImpl extends ReadWriteDAOImpl<UserDto, Long> implements UserDtoDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDto> getUserDtoList() {

        List<UserDto> getAllUsers = new ArrayList<>();

        try {
            getAllUsers = entityManager.createQuery("SELECT " +
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
                    "FROM User u")
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

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return getAllUsers;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<UserDto> getUserDtoById(Long id) {

        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
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
                "FROM User u WHERE u.id = " + id)
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
                }));
    }

    @Override
    public Long getCountNewUsersByReputation(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r " +
                "WHERE r.user.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getListNewUsersByReputation(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.persistDateTime, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)" +
                "FROM Reputation as r WHERE r.user.persistDateTime > :data " +
                "GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }

    @Override
    public Long getCountUsersByCreationDate(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r " +
                "WHERE r.user.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getListUsersByCreationDate(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.persistDateTime, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)" +
                "FROM Reputation as r WHERE r.user.persistDateTime > :data " +
                "GROUP BY r.user.id ORDER BY r.user.persistDateTime DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }

    @Override
    public Long getCountUsersByQuantityEditedText(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT e.user.id) FROM Editor as e " +
                "WHERE e.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getListUsersByQuantityEditedText(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "e.id, " +
                "e.persistDateTime, " +
                "e.user.id, " +
                "e.user.fullName, " +
                "e.user.about, " +
                "e.user.city, " +
                "e.user.imageUser, " +
                "SUM(e.countChanges)," +
                "(SELECT SUM(r.reputationCount) FROM Reputation as r WHERE e.user.id = r.user.id AND r.persistDateTime > :data GROUP BY r.user.id) " +
                "FROM Editor as e WHERE e.persistDateTime > :data GROUP BY e.user.id ORDER BY SUM(e.countChanges) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
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
    public List<UserDto> getListUsersByNameToSearch(String name, int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.persistDateTime, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)" +
                "FROM Reputation as r WHERE r.persistDateTime > :data AND r.user.fullName LIKE CONCAT(:searchKeyword, '%') " +
                "GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setParameter("data", data)
                .setParameter("searchKeyword", name.toLowerCase())
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }

    @Override
    public Long getCountUsersByReputation(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r " +
                "WHERE r.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getListUsersByReputation(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.persistDateTime, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)" +
                "FROM Reputation as r WHERE r.persistDateTime > :data " +
                "GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();

        return listUsers.isEmpty() ? Collections.emptyList() : listUsers;
    }

    @Override
    public Long getCountUsersByVoice(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("SELECT COUNT(DISTINCT r.user.id) FROM Reputation as r " +
                "WHERE r.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDto> getListUsersByVoice(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "r.id, " +
                "r.user.id, " +
                "r.user.persistDateTime, " +
                "r.user.fullName, " +
                "r.user.about, " +
                "r.user.city, " +
                "r.user.imageUser, " +
                "SUM(r.reputationCount)" +
                "FROM Reputation as r WHERE r.persistDateTime > :data " +
                "GROUP BY r.user.id ORDER BY SUM(r.reputationCount) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
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
    public List<UserDto> getListUsersByModerator() {
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
                "m.persistDateTime, " +
                "m.user.id, " +
                "m.user.fullName, " +
                "m.user.city, " +
                "m.user.imageUser, " +
                "SUM(r.reputationCount)" +
                "FROM Moderator as m JOIN Reputation as r ON m.user.id = r.user.id " +
                "GROUP BY r.user.id ")
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return null;
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
