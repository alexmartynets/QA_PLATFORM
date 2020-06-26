package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.Optional;

public interface TagDAO extends ReadWriteDAO<Tag, Long> {
    Optional<Long> getTagIdByName(String name);
}
