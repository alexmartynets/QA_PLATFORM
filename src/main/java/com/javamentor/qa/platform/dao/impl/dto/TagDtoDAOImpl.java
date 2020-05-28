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

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPaginationPopular(int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery("select " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "t.questions.size, " +
                "t.persistDateTime, " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :day), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :month), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :year) " +
                "from Tag t order by t.questions.size desc");

        return getTags(query, pageSize, pageNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPaginationName(int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery("select " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "t.questions.size, " +
                "t.persistDateTime, " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :day), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :month), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :year) " +
                "from Tag t order by t.name asc");

        return getTags(query, pageSize, pageNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPaginationDate(int pageSize, int pageNumber) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery("select " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "t.questions.size, " +
                "t.persistDateTime, " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :day), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :month), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :year) " +
                "from Tag t order by t.persistDateTime desc");

        return getTags(query, pageSize, pageNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsSearch(String word) {
        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery("select " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "t.questions.size, " +
                "t.persistDateTime, " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :day), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :month), " +
                "(select count (q.id) from t.questions q where q.persistDateTime >= :year) " +
                "from Tag t where t.name like :search order by t.questions.size desc")
                .setParameter("search", "%" + word + "%");

        return getTags(query, 36, 1);
    }

    @Override
    public Long getFinalPage(int pageSize) {
        long totalCount = ((Number) entityManager.createQuery
                ("select count(t.id) from Tag t").getSingleResult()).longValue();
        long finalPage = (totalCount / (long) pageSize) + 1;
        return ((Long) finalPage);
    }

    @SuppressWarnings("unchecked")
    private List<TagDto> getTags(Query<TagDto> query, int pageSize, int pageNumber) {
        return query.setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .setParameter("day", LocalDateTime.now().minusHours(24))
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
                                .questionMonthCount(((Number) objects[6]).intValue())
                                .questionYearCount(((Number) objects[7]).intValue())
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
