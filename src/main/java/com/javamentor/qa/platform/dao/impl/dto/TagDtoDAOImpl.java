package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TagDtoDAOImpl extends ReadWriteDAOImpl<TagDto, Long> implements TagDtoDAO {
    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, List<TagDto>> findAllTagsDtoPagination(int pageSize, int pageNumber) {
        List<TagDto> list = entityManager.createQuery("select " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "(select count (t.id) from 'question_has_tag' where 'tag_id' = t.id ) " +
                " from Tag t", Tag.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return TagDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .name(String.valueOf(objects[1]))
                                .description(String.valueOf(objects[2]))
                                .build();
                    }

                    @Override
                    public List transformList(List list) {

                        return list;
                    }
                })
                .getResultList();

        int totalCount = (int) entityManager.createQuery
                ("select count(t.id) from Tag t").getSingleResult();
        Integer finalPage = (totalCount / pageSize) + 1;
        Map<Integer, List<TagDto>> map = new HashMap<>();
        map.put(finalPage, list);
        return map;
    }
}
