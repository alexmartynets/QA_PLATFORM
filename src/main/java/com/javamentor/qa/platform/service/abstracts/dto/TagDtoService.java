package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;
import java.util.Map;

public interface TagDtoService {
    Map<Integer, List<TagDto>> findAllTagsDtoPagination(int pageSize, int pageNumber);
}
