package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserBadgesDAO;
import com.javamentor.qa.platform.models.entity.Badges;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.UserBadges;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserBadgesDAOImpl extends ReadWriteDAOImpl<UserBadges, Long> implements UserBadgesDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserBadges> getUserBadges(User user) {
        List<UserBadges> userBadgesList = entityManager.createQuery("SELECT " +
                "ub.id, " +
                "ub.ready, " +
                "b.id, " +
                "b.reputationForMerit, " +
                "b.badges " +
                "FROM UserBadges ub JOIN Badges b ON ub.badges.id = b.id " +
                "WHERE ub.user.id = :user_id AND ub.ready = false")
                .setParameter("user_id", user.getId())
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        Badges badges = Badges.builder()
                                .id((Long) objects[2])
                                .reputationForMerit((Integer) objects[3])
                                .badges((String) objects[4])
                                .build();
                        return UserBadges.builder()
                                .id((Long) objects[0])
                                .user(user)
                                .badges(badges)
                                .ready((Boolean) objects[1])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
        return userBadgesList;
    }
}
