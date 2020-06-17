package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserBadgesDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.UserBadgesDto;
import com.javamentor.qa.platform.models.entity.Badges;
import com.javamentor.qa.platform.models.entity.user.User;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserBadgesDtoDAOImpl extends ReadWriteDAOImpl<UserBadgesDto, Long> implements UserBadgesDtoDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserBadgesDto> getUserBadgesDto(Long user_id) {
        List<UserBadgesDto> userBadgesDtoList = entityManager.createQuery("SELECT " +
                "ub.id, " +
                "ub.ready, " +
                "ub.countOfBadges, " +
                "(SELECT b.id FROM Badges b WHERE ub.badges.id = b.id), " +
                "(SELECT b.count FROM Badges b WHERE ub.badges.id = b.id), " +
                "(SELECT b.badges FROM Badges b WHERE ub.badges.id = b.id) " +
                "FROM UserBadges ub WHERE ub.user.id = :user_id AND ub.ready = false")
                .setParameter("user_id", user_id)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        User user = User.builder().id(user_id).build();
                        Badges badges = Badges.builder()
                                .id((Long) objects[3])
                                .count((Integer) objects[4])
                                .badges((String) objects[5])
                                .build();
                        return UserBadgesDto.builder()
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
        return userBadgesDtoList;
    }
}
