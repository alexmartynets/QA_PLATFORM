package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDtoDaoImpl extends ReadWriteDaoImpl<UserDto, Long> implements UserDtoDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDto> getUserDtoList() {
        List<UserDto> getAllUsers = new ArrayList<>();

        try {
            getAllUsers = entityManager.createQuery("SELECT " +
                    "u.id, " +
                    "u.email, " +
                    "u.password, " +
                    "u.role.name " +
                    "FROM User u")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            return UserDto.builder()
                                    .id(((Number)objects[0]).longValue())
                                    .email((String) objects[1])
                                    .password((String) objects[2])
                                    .role((String) objects[3])
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

    @Override
    public UserDto getUserDtoById(Long id) {
        UserDto userDto = null;
        try {
            userDto = (UserDto) entityManager.createQuery("SELECT u.id, " +
                    "u.email, " +
                    "u.password, " +
                    "u.role.name " +
                    "FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            return UserDto.builder()
                                    .id(((Number) objects[0]).longValue())
                                    .email((String) objects[1])
                                    .password((String) objects[2])
                                    .role((String) objects[3])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            return list;
                        }
                    })
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDto;
    }
}
