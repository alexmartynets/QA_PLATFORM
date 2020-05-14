package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDtoDAOImpl extends ReadWriteDAOImpl<UserDto, Long> implements UserDtoDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDto> getUserDtoList() {

        List<UserDto>  getAllUsers = entityManager.createQuery("SELECT " +
                    "u.id, " +
                    "u.fullName, " +
                    "u.email, " +
                    "u.password, " +
                    "u.role.name " +
                    "FROM User u")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            return new UserDto.Builder()
                                    .withId(((Number) objects[0]).longValue())
                                    .withFullName((String) objects[1])
                                    .withEmail((String) objects[2])
                                    .withPassword((String) objects[3])
                                    .withRole((String) objects[4])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            return list;
                        }
                    })
                    .getResultList();

        return getAllUsers.isEmpty()? Collections.emptyList(): getAllUsers;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<UserDto> getUserDtoById(Long id) {

        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
                "u.id, " +
                "u.fullName, " +
                "u.email, " +
                "u.password, " +
                "u.role.name " +
                "FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return new UserDto.Builder()
                                .withId(((Number) objects[0]).longValue())
                                .withFullName((String) objects[1])
                                .withEmail((String) objects[2])
                                .withPassword((String) objects[3])
                                .withRole((String) objects[4])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }));
    }
}
