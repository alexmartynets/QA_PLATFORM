package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.EditorDto;
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

        } catch (Exception e) {
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
    public Long getCountNewUsers(long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        return entityManager.createQuery("select count(u.id) from  User as u WHERE u.persistDateTime > :data", Long.class)
                .setParameter("data", data)
                .getSingleResult();
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
    public List<UserDto> getListNewUsers(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<UserDto> listUsers = entityManager.createQuery("SELECT " +
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
                "FROM User as u WHERE u.persistDateTime > :data")
                .setParameter("data", data)
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
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EditorDto> getListUsersByQuantityEditedText(int page, int count, long weeks) {
        LocalDateTime data = LocalDateTime.now().minusWeeks(weeks);
        List<EditorDto> listUsers = entityManager.createQuery("SELECT " +
                "e.id, " +
                "e.user.id, " +
                "e.user.fullName, " +
                "e.user.about, " +
                "e.user.city, " +
                "e.user.imageUser, " +
                "SUM(e.countChanges)" +
                "FROM Editor e WHERE e.persistDateTime > :data " +
                "GROUP BY e.user.id ORDER BY SUM(e.countChanges) DESC")
                .setParameter("data", data)
                .setFirstResult(count * (page - 1))
                .setMaxResults(count)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return EditorDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .editorId(((Number) objects[1]).longValue())
                                .fullName((String) objects[2])
                                .about((String) objects[3])
                                .city((String) objects[4])
                                .imageUser((byte[]) objects[5])
                                .countChanges(((Number) objects[6]).longValue())
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