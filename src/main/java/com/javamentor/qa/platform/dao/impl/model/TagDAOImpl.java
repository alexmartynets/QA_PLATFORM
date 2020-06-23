package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
public class TagDAOImpl extends ReadWriteDAOImpl<Tag, Long> implements TagDAO {

//    @Transactional
//    public Tag getTagByName(String name) {
//        try {
//            return entityManager.createQuery(
//                    "select t from Tag t where t.name = :name", Tag.class)
//                    .setParameter("name", name)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }
}
