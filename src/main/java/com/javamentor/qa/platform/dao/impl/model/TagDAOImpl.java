package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class TagDAOImpl extends ReadWriteDAOImpl<Tag, Long> implements TagDAO {
    @Override
    public Tag getTagByName(String tagName) {
        try {
            return entityManager.createQuery("select t from Tag t where t.name=:tagName", Tag.class)
                    .setParameter("tagName", tagName)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }
}
