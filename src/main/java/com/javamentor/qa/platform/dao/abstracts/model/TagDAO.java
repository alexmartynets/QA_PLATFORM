package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.dto.UserTagsDto;
import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.Optional;

import java.util.List;

public interface TagDAO extends ReadWriteDAO<Tag, Long> {
    Optional<Long> getTagIdByName(String name);
    List<UserTagsDto> getUserTags(Long userId, Integer page);
    Long getCountOfUserTags(Long userId);
}
