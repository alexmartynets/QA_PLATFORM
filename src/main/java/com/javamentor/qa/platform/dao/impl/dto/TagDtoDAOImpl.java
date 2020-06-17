package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TagDtoDAOImpl extends ReadWriteDAOImpl<TagDto, Long> implements TagDtoDAO {

    private final String HQL = "select " +
            "t.id, " +
            "t.name, " +
            "t.description, " +
            "t.questions.size, " +
            "t.persistDateTime, " +
            "(select count (q.id) from t.questions q where q.persistDateTime >= :day), " +
            "(select count (q.id) from t.questions q where q.persistDateTime >= :week), " +
            "(select count (q.id) from t.questions q where q.persistDateTime >= :month), " +
            "(select count (q.id) from t.questions q where q.persistDateTime >= :year) ";

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPaginationPopular(int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery(HQL +
                "from Tag t order by t.questions.size desc");

        return getTags(query, pageSize, pageNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPaginationName(int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery(HQL +
                "from Tag t order by t.name asc");

        return getTags(query, pageSize, pageNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPaginationDate(int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery(HQL +
                "from Tag t order by t.persistDateTime desc");

        return getTags(query, pageSize, pageNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsSearch(String word, int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery(HQL +
                "from Tag t where t.name like :search order by t.questions.size desc")
                .setParameter("search", "%" + word + "%");

        return getTags(query, pageSize, pageNumber);
    }

    @Override
    public Long getTotalEntitiesCount() {
        return ((Number) entityManager.createQuery
                ("select count(t.id) from Tag t").getSingleResult()).longValue();
    }

    @SuppressWarnings("unchecked")
    private List<TagDto> getTags(Query<TagDto> query, int pageSize, int pageNumber) {
        return query.setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .setParameter("day", LocalDateTime.now().minusHours(24))
                .setParameter("week", LocalDateTime.now().minusDays(7))
                .setParameter("month", LocalDateTime.now().minusMonths(1))
                .setParameter("year", LocalDateTime.now().minusYears(1))
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return TagDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .name((String) objects[1])
                                .description((String) objects[2])
                                .questionCount(((Number) objects[3]).intValue())
                                .persistDateTime((LocalDateTime) objects[4])
                                .questionTodayCount(((Number) objects[5]).intValue())
                                .questionWeekCount(((Number) objects[6]).intValue())
                                .questionMonthCount(((Number) objects[7]).intValue())
                                .questionYearCount(((Number) objects[8]).intValue())
                                .build();
                    }

                    @Override
                    public List<TagDto> transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
    }
}
