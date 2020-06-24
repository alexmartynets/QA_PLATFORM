package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.Optional;

public interface TagService extends ReadWriteService<Tag, Long> {
    Long checkOrPersists(String tagName);
}
