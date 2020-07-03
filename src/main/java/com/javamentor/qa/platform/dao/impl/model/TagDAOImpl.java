package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.dto.UserTagsDto;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.script.ScriptEngine;
import java.util.List;

import java.util.Optional;

import javax.persistence.NoResultException;

@Repository
public class TagDAOImpl extends ReadWriteDAOImpl<Tag, Long> implements TagDAO {

    @Override
    public Tag getTagByName(String tagName) {
        try {
            return entityManager.createQuery("select t from Tag t where t.name=:tagName", Tag.class)
                    .setParameter("tagName", tagName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserTagsDto> getUserTags(Long userId, Integer page){
        List<UserTagsDto> list = entityManager.createQuery("SELECT " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "(SELECT COUNT(qu.id) FROM Question qu JOIN qu.tags tu WHERE tu.id = t.id AND qu.user.id = :userId), " +
                "(SELECT COUNT(DISTINCT qa.id) FROM Question qa LEFT JOIN Answer a ON qa.id = a.question.id JOIN qa.tags ta " +
                    "WHERE ta.id = t.id AND a.user.id = :userId AND qa.user.id <> a.user.id) " +
                "FROM Tag t ")
                .setFirstResult((page - 1) * 42)
                .setMaxResults(42)
                .setParameter("userId", userId)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        Long sum = Long.sum((Long)tuple[3], (Long)tuple[4]);
                        return UserTagsDto.builder()
                                .tagId((Long) tuple[0])
                                .tagName((String) tuple[1])
                                .tagDescription((String) tuple[2])
                                .countOfTag(sum)
                                .build();
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                }).getResultList();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long getCountOfUserTags(Long userId){
        return entityManager.createQuery("SELECT COUNT(t.id) FROM Tag t ", Long.class).getSingleResult();
    }

    @Override
    public Optional<Long> getTagIdByName(String name) {
            return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT t.id FROM Tag t WHERE t.name = :name", Long.class)
                    .setParameter("name", name));
    }
}
