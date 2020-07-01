package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagResourceController {
    private final TagDtoService tagDtoService;

    @Autowired
    public TagResourceController(TagDtoService tagDtoService) {
        this.tagDtoService = tagDtoService;
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllMainTagsSortedByFrequency() {
        return ResponseEntity.ok(tagDtoService.getAllMainTagsSortedByFrequency());
    }

    @GetMapping("/relatedTags/{mainTagId}")
    public ResponseEntity<List<TagDto>> getRelatedTags(@PathVariable Long mainTagId) {
        return ResponseEntity.ok(tagDtoService.getRelatedTags(mainTagId));
    }
}
