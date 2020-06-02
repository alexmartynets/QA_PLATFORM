package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.RelatedTagService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import com.javamentor.qa.platform.webapp.converter.TagConverter;
import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/user/tag")
public class TagResourceController {

    private final TagDtoService tagDtoService;
    private final TagService tagService;
    private final RelatedTagService relatedTagService;
    private final TagConverter tagConverter;

    public TagResourceController(TagDtoService tagDtoService, TagService tagService, RelatedTagService relatedTagService, TagConverter tagConverter) {
        this.tagDtoService = tagDtoService;
        this.tagService = tagService;
        this.relatedTagService = relatedTagService;
        this.tagConverter = tagConverter;
    }

    @GetMapping
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByPopular(@RequestParam("pageSize") int pageSize,
                                                                        @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPagination(pageSize, pageNumber));
    }

    @GetMapping("/name")
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByName(@RequestParam("pageSize") int pageSize,
                                                                     @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPaginationName(pageSize, pageNumber));
    }

    @GetMapping("/new")
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByDate(@RequestParam("pageSize") int pageSize,
                                                                     @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPaginationDate(pageSize, pageNumber));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TagDto>> getAllTagsSearch(@RequestParam("tagName") String word) {
        return ResponseEntity.ok(tagDtoService.findAllTagsSearch(word));
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<TagDto> addMainTag(@RequestBody @Valid TagDto tagDto) {
        tagService.persist(tagConverter.dtoToTag(tagDto));
        return ResponseEntity.ok(tagDto);
    }

    @Validated(OnCreate.class)
    @PostMapping("/{mainTagId}")
    public ResponseEntity<TagDto> addChildTag(@RequestBody @Valid TagDto tagDto,
                                              @PathVariable @NotNull Long mainTagId) {
        tagService.persistChildTag(tagConverter.dtoToTag(tagDto), mainTagId);
        return ResponseEntity.ok(tagDto);
    }

    @Validated(OnUpdate.class)
    @PutMapping
    public ResponseEntity<TagDto> updateTag(@RequestBody @Valid TagDto tagDto) {
        tagService.update(tagConverter.dtoToTag(tagDto));
        return ResponseEntity.ok(tagDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable @NotNull Long id) {
        tagService.deleteByKeyCascadeIgnore(id);
        relatedTagService.deleteRelTagsByTagId(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
