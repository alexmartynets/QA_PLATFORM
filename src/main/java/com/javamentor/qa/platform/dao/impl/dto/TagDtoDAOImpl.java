package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDtoDAOImpl extends ReadWriteDAOImpl<TagDto, Long> implements TagDtoDAO {

    @Override
    @SuppressWarnings({"unchecked", "deprecated"})
    public List<TagDto> getAllMainTagsSortedByFrequency() {
        List<TagDto> mainDtoTags = new ArrayList<>();
        try {
            mainDtoTags = entityManager.createQuery("SELECT " +
                    "t.id, " +
                    "t.name, " +
                    "count(t.id) as i " +
                    "FROM Tag t " +
                    "JOIN t.questions " +
                    /*"JOIN RelatedTag rt " +
                    "on t.id <> rt.childTag.id " +*/
                    "GROUP BY t.id order by i desc")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            return TagDto.builder()
                                    .id((Long) objects[0])
                                    .name((String) objects[1])
                                    .questionTagCount((Long) objects[2])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            return list;
                        }
                    })
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mainDtoTags;
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecated"})
    public List<TagDto> getRelatedTags(Long mainTagId) {
        List<TagDto> relatedDtoTags = new ArrayList<>();
        try {
            relatedDtoTags = entityManager.createQuery("SELECT " +
                    "t.id, " +
                    "t.name, " +
                    "count(t.id) as i " +
                    "FROM Tag as t " +
                    "JOIN t.questions " +
                    "WHERE t.id in " +
                    "(SELECT rT.childTag.id FROM RelatedTag rT JOIN Tag t on rT.mainTag.id = t.id WHERE rT.mainTag.id = :mTI)" +
                    "group by t.id order by i desc")
                    .setParameter("mTI", mainTagId)
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            return TagDto.builder()
                                    .id((Long) objects[0])
                                    .name((String) objects[1])
                                    .questionTagCount((Long) objects[2])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            return list;
                        }
                    })
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return relatedDtoTags;
    }
}
