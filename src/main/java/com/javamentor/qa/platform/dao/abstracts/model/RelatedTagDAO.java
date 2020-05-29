package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.RelatedTag;

public interface RelatedTagDAO extends ReadWriteDAO<RelatedTag, Long> {
    void deleteRelTagsByTagId(Long tagId);
}
