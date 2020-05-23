package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;

public interface TagDtoDAO {
    List<TagDto> findAllTagsDtoPagination(int pageSize, int pageNumber);
    String getFinalPage (int pageSize);

}
