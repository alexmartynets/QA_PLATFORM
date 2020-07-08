package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.TagDto;
import javafx.util.Pair;

import java.util.List;

public interface TagDtoService {
    Pair<Long, List<TagDto>> findAllTagsDtoPagination(int pageSize, int pageNumber);
    Pair<Long, List<TagDto>> findAllTagsDtoPaginationName(int pageSize, int pageNumber);
    Pair<Long, List<TagDto>> findAllTagsDtoPaginationDate(int pageSize, int pageNumber);
    Pair<Long, List<TagDto>> findAllTagsSearch(String word, int pageSize, int pageNumber);


    List<TagDto> getAllMainTagsSortedByFrequency();

    List<TagDto> getRelatedTags(Long mainTagId);
}
