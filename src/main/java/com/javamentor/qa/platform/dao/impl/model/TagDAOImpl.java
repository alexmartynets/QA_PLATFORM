package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDAO;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TagDAOImpl extends ReadWriteDAOImpl<Tag, Long> implements TagDAO {

    @Override
    public Optional<Long> getTagIdByName(String name) {
            return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT t.id FROM Tag t WHERE t.name = :name", Long.class)
                    .setParameter("name", name));
    }
}
