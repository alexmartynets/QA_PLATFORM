package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import lombok.SneakyThrows;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.io.Reader;
import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TagDtoDAOImpl extends ReadWriteDAOImpl<TagDto, Long> implements TagDtoDAO {
    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, List<TagDto>> findAllTagsDtoPagination(int pageSize, int pageNumber) {
        List<TagDto> list = entityManager.createQuery
                ("select " +
                        "t.id, " +
                        "t.name, " +
//                        "t.description, " +
                        "(select count (q) from Tag t, Question q where t.id = t.id ) " +
                        " from Tag t")
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {

                        return TagDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .name((String)objects[1])
//                                .description((String) objects[2])
                                .questionCount((Integer) objects[2])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {

                        return list;
                    }
                })
                .getResultList();

        int totalCount = (Integer) entityManager.createQuery
                ("select count(t) from Tag t").getSingleResult();
        Integer finalPage = (totalCount / pageSize) + 1;
        Map<Integer, List<TagDto>> map = new HashMap<>();
        map.put(finalPage, list);
        return map;
    }
}
