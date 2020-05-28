package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDAO;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDtoServiceImpl implements TagDtoService {

    @Autowired
    private TagDtoDAO tagDtoDAO;

    @Override
    public Pair<Long, List<TagDto>> findAllTagsDtoPagination(int pageSize, int pageNumber) {
        pageSize = pageSize <= 0 ? 1 : pageSize;
        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        List<TagDto> list = tagDtoDAO.findAllTagsDtoPaginationPopular(pageSize, pageNumber);
        Long finalPage = tagDtoDAO.getFinalPage(pageSize);
        return new Pair<>(finalPage, list);
    }

    @Override
    public Pair<Long, List<TagDto>> findAllTagsDtoPaginationName(int pageSize, int pageNumber) {
        pageSize = pageSize <= 0 ? 1 : pageSize;
        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        List<TagDto> list = tagDtoDAO.findAllTagsDtoPaginationName(pageSize, pageNumber);
        Long finalPage = tagDtoDAO.getFinalPage(pageSize);
        return new Pair<>(finalPage, list);
    }

    @Override
    public Pair<Long, List<TagDto>> findAllTagsDtoPaginationDate(int pageSize, int pageNumber) {
        pageSize = pageSize <= 0 ? 1 : pageSize;
        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        List<TagDto> list = tagDtoDAO.findAllTagsDtoPaginationDate(pageSize, pageNumber);
        Long finalPage = tagDtoDAO.getFinalPage(pageSize);
        return new Pair<>(finalPage, list);
    }

    @Override
    public List<TagDto> findAllTagsSearch(String word) {
        word = word.trim();
        return word.isEmpty()? tagDtoDAO.findAllTagsDtoPaginationPopular(10,1):tagDtoDAO.findAllTagsSearch(word);
    }
}
