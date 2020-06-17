package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ReputationDtoDAOImpl extends ReadWriteDAOImpl<ReputationDto, Long> implements ReputationDtoDAO {

    @SuppressWarnings("unchecked")
    @Override
    public ReputationDto getUserReputation(Long user_id) {
        ReputationDto reputationDto = (ReputationDto) entityManager.createQuery("SELECT " +
                "r.user.fullName, " +
                "r.count, " +
                "r.persistDate, " +
                "(SELECT SUM(r.count) FROM r WHERE r.user.id = :user_id) " +
                "FROM Reputation r WHERE r.user.id = :user_id AND r.persistDate = :persistDate")
                .setParameter("persistDate", LocalDate.now())
                .setParameter("user_id", user_id)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        User user = User.builder().id(user_id).build();
                        return ReputationDto.builder()
                                .user(user)
                                .count((Integer) objects[1])
                                .persistDate((LocalDate) objects[2])
                                .allCount((Long) objects[3])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getSingleResult();
        return reputationDto;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ReputationDto findByUserIdAndDate(User user) {
        ReputationDto reputation = null;
        try {
            reputation = (ReputationDto) entityManager.createQuery("SELECT " +
                    "r.id, " +
                    "r.count, " +
                    "r.persistDate, " +
                    "(SELECT SUM(r.count) FROM r WHERE r.user.id = :user_id) " +
                    "FROM Reputation r WHERE r.user.id = :user_id AND r.persistDate = :persistDate")
                    .setParameter("persistDate", LocalDate.now())
                    .setParameter("user_id", user.getId())
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            return ReputationDto.builder()
                                    .user(user)
                                    .id((Long) objects[0])
                                    .count((Integer) objects[1])
                                    .persistDate((LocalDate) objects[2])
                                    .allCount((Long) objects[3])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            return list;
                        }
                    })
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return reputation;
    }
}
