package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;

public interface TagDtoDAO {
    List<TagDto> findAllTagsDtoPaginationPopular(int pageSize, int pageNumber);
    List<TagDto> findAllTagsDtoPaginationName(int pageSize, int pageNumber);
    List<TagDto> findAllTagsDtoPaginationDate(int pageSize, int pageNumber);
    List<TagDto> findAllTagsSearch(String word, int pageSize, int pageNumber);
    Long getTotalEntitiesCount();
    Long getTotalEntitiesCountSearch(String word);

}

    List<TagDto> getAllMainTagsSortedByFrequency();

    List<TagDto> getRelatedTags(Long mainTagId);
}