package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/tag")
public class TagResourceController {

    private final TagDtoService tagDtoService;

    public TagResourceController(TagDtoService tagDtoService) {
        this.tagDtoService = tagDtoService;
    }

    @GetMapping("{pageSize}/{pageNumber}")
    public ResponseEntity<Map<Integer, List<TagDto>>> getAllTags(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPagination(pageSize, pageNumber));
    }
}
