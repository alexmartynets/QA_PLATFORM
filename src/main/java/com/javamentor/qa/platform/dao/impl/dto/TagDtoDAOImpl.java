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
        List<TagDto> list = entityManager.createNativeQuery("SELECT " +
                "tag.id, " +
                "tag.description, " +
                "tag.name, " +
                "COUNT(question_has_tag.question_id) " +
                "FROM question_has_tag " +
                "INNER JOIN question " +
                "ON question.id = qa_platform.question_has_tag.question_id " +
                "INNER JOIN tag " +
                "ON question_has_tag.tag_id = qa_platform.tag.id " +
                "GROUP BY tag.id")
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return TagDto.builder()
                                .id(((Number) objects[0]).longValue())
                                .name((String)objects[1])
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

        int totalCount = ((Number) entityManager.createQuery
                ("select count(t) from Tag t").getSingleResult()).intValue();
        Integer finalPage = (totalCount / pageSize) + 1;
        Map<Integer, List<TagDto>> map = new HashMap<>();
        map.put(finalPage, list);
        return map;
    }
}
