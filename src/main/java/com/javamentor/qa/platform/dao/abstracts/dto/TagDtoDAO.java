package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;

public interface TagDtoDAO {
    List<TagDto> getAllMainTagsSortedByFrequency();

    List<TagDto> getRelatedTags(Long mainTagId);
}