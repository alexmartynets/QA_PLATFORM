package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import com.javamentor.qa.platform.webapp.converter.TagConverter;
import javafx.util.Pair;
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
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByPopular(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPagination(pageSize, pageNumber));
    }

    @GetMapping("/name/{pageSize}/{pageNumber}")
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByName(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPaginationName(pageSize, pageNumber));
    }

    @GetMapping("/new/{pageSize}/{pageNumber}")
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByDate(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPaginationDate(pageSize, pageNumber));
    }

    @GetMapping("/search/{word}")
    public ResponseEntity <List<TagDto>> getAllTagsSearch(@PathVariable String word) {
        return ResponseEntity.ok(tagDtoService.findAllTagsSearch(word));
    }

    @PostMapping
    public ResponseEntity<TagDto> addTag(@RequestBody TagDto tagDto){
        tagService.persist(tagConverter.dtoToTag(tagDto));
        return ResponseEntity.ok(tagDto);
    }
}
