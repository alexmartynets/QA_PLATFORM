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
                "ub.countOfBadges, " +
                "(SELECT b.id FROM Badges b WHERE ub.badges.id = b.id), " +
                "(SELECT b.count FROM Badges b WHERE ub.badges.id = b.id), " +
                "(SELECT b.badges FROM Badges b WHERE ub.badges.id = b.id) " +
                "FROM UserBadges ub WHERE ub.user.id = :user_id AND ub.ready = false")
                .setParameter("user_id", user.getId())
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        Badges badges = Badges.builder()
                                .id((Long) objects[3])
                                .count((Integer) objects[4])
                                .badges((String) objects[5])
                                .build();
                        return UserBadges.builder()
                                .id((Long) objects[0])
                                .user(user)
                                .badges(badges)
                                .ready((Boolean) objects[1])
                                .countOfBadges((Integer) objects[2])
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
