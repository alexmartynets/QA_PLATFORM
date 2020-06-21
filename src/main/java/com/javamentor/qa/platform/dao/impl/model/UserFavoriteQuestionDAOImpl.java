package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class UserFavoriteQuestionDAOImpl
        extends ReadWriteDAOImpl<UserFavoriteQuestion, Long>
        implements UserFavoriteQuestionDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<QuestionDto> getUserFavorite(Long user_id, String sort, int page) {
        List<QuestionDto> favoriteQuestionDto = entityManager.createQuery("SELECT " +
                "q.id, " +
                "q.title, " +
                "q.viewCount, " +
                "q.countValuable, " +
                "(SELECT COUNT(uf.question.id) FROM uf WHERE uf.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id), " +
                "q.persistDateTime " +
                "FROM Question q " +
                "LEFT JOIN UserFavoriteQuestion uf ON q.id = uf.question.id " +
                "WHERE uf.user.id = :user_id " +
                "ORDER BY "+ sort +" DESC")
                .setParameter("user_id", user_id)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        return QuestionDto.builder()
                                .id((Long) tuple[0])
                                .title((String) tuple[1])
                                .viewCount((Integer) tuple[2])
                                .countValuable((Integer) tuple[3])
                                .countFavorite(((Number) tuple[4]).intValue())
                                .isHelpful((Boolean) tuple[5])
                                .persistDateTime((LocalDateTime) tuple[6])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                       return list;
                    }
                }).getResultList();

        return favoriteQuestionDto;
    }
}
