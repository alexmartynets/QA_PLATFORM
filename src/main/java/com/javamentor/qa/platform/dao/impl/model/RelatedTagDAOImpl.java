package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.RelatedTagDAO;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RelatedTagDAOImpl extends ReadWriteDAOImpl<RelatedTag, Long> implements RelatedTagDAO {

    @Override
    @Transactional
    public void deleteRelTagsByTagId(Long tagId) {
        entityManager.createQuery("delete from RelatedTag rt where rt.mainTag.id = :tagId or rt.childTag.id = :tagId")
                .setParameter("tagId", tagId)
                .executeUpdate();
    }
}
