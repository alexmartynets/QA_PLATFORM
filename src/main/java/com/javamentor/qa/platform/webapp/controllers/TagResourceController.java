package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import com.javamentor.qa.platform.webapp.converter.TagConverter;
import com.sun.tools.javac.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/tag")
public class TagResourceController {

    private final TagDtoService tagDtoService;
    private final TagService tagService;
    private final TagConverter tagConverter;

    public TagResourceController(TagDtoService tagDtoService, TagService tagService, TagConverter tagConverter) {
        this.tagDtoService = tagDtoService;
        this.tagService = tagService;
        this.tagConverter = tagConverter;
    }

    @GetMapping("{pageSize}/{pageNumber}")
    public ResponseEntity<Pair<String, List<TagDto>>> getAllTags(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPagination(pageSize, pageNumber));
    }

    @PostMapping
    public ResponseEntity<TagDto> addTag(@RequestBody TagDto tagDto){
        tagService.persist(tagConverter.dtoToTag(tagDto));
        return ResponseEntity.ok(tagDto);
    }
}
