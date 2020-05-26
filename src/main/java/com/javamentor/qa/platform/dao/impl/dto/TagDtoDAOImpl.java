package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.hibernate.search.jpa.Search;

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

        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);
        entityManager.getTransaction().begin();

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Tag.class).get();
        org.apache.lucene.search.Query query = qb

                .keyword()
                .wildcard()
                .onField("name")
                .matching(word + "** ")

                .createQuery();

        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(query, Tag.class);
//        Query<TagDto> query = (Query<TagDto>) entityManager.createQuery("select " +
//                "t.id, " +
//                "t.name, " +
//                "t.description, " +
//                "t.questions.size, " +
//                "t.persistDateTime " +
//                "from Tag t where t.name having 'Phone' order by t.questions.size desc");
        return persistenceQuery
                .setFirstResult(0)
                .setMaxResults(36)
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
                                .build();
                    }

                    @Override
                    public List<TagDto> transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
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
