package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDtoServiceImpl implements TagDtoService {
    private final TagDtoDAO tagDtoDAO;

    @Autowired
    public TagDtoServiceImpl(TagDtoDAO tagDtoDAO) {
        this.tagDtoDAO = tagDtoDAO;
    }

    @Override
    public List<TagDto> getAllMainTagsSortedByFrequency() {
        return tagDtoDAO.getAllMainTagsSortedByFrequency();
    }

    @Override
    public List<TagDto> getRelatedTags(Long mainTagId) {
        return tagDtoDAO.getRelatedTags(mainTagId);
    }
}
