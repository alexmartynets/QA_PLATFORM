package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;

import com.sun.tools.javac.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDtoServiceImpl implements TagDtoService {

    @Autowired
    private TagDtoDAO tagDtoDAO;

    @Override
    public Pair<String, List<TagDto>> findAllTagsDtoPagination(int pageSize, int pageNumber) {
        pageSize = pageSize <= 0 ? 1 : pageSize;
        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        List<TagDto> list = tagDtoDAO.findAllTagsDtoPagination(pageSize, pageNumber);
        String finalPage = tagDtoDAO.getFinalPage(pageSize);
        return new Pair<>(finalPage, list);
    }
}
