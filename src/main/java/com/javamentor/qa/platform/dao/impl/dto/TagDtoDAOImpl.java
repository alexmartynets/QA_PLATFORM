package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.List;

@Repository
public class TagDtoDAOImpl extends ReadWriteDAOImpl<TagDto, Long> implements TagDtoDAO {
    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> findAllTagsDtoPagination(int pageSize, int pageNumber) {

        List<TagDto> list = entityManager.createQuery("select " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "t.questions.size " +
                "from Tag t")
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return TagDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .name((String) objects[1])
                                .description((String) objects[2])
                                .questionCount(((Number) objects[3]).intValue())
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
        return list;
    }

    @Override
    public String getFinalPage(int pageSize) {
        long totalCount = ((Number) entityManager.createQuery
                ("select count(t) from Tag t").getSingleResult()).longValue();
        long finalPage = (totalCount / (long) pageSize) + 1;
        return ((Long)finalPage).toString();
    }
}
