package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.RelatedTagService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import com.javamentor.qa.platform.webapp.converter.TagConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/user/tag", produces = "application/json")
@Api(value="TagApi", description = "Операции с тэгами (создание, изменение, получение списков с сортировками, поиск по имени тэга, удаление)")
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
    @ApiOperation(value = "Получение количества страниц и списка тэгов на странице отсортированных по популярности. " +
            "Параметры запроса: " +
            "'pageSize'- количество элементов на странице и " +
            "'pageNumber' номер страницы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список тэгов получен")
    })
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByPopular(@RequestParam("pageSize") int pageSize,
                                                                        @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPagination(pageSize, pageNumber));
    }

    @GetMapping("/name")
    @ApiOperation(value = "Получение количества страниц и списка тэгов на странице отсортированных по имени. " +
            "Параметры запроса: " +
            "'pageSize'- количество элементов на странице и " +
            "'pageNumber' номер страницы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список тэгов получен")
    })
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByName(@RequestParam("pageSize") int pageSize,
                                                                     @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPaginationName(pageSize, pageNumber));
    }

    @GetMapping("/new")
    @ApiOperation(value = "Получение количества страниц и списка тэгов на странице отсортированных по дате добавления. " +
            "Параметры запроса: " +
            "'pageSize'- количество элементов на странице и " +
            "'pageNumber' номер страницы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список тэгов получен")
    })
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsByDate(@RequestParam("pageSize") int pageSize,
                                                                     @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsDtoPaginationDate(pageSize, pageNumber));
    }

    @GetMapping("/search")
    @ApiOperation(value = "Получение тэгов по заданномму в параметре имени, отсортированных по популярности. " +
            "Параметры запроса: " +
            "'tagName'- имя искомого тэга")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список тэгов получен")
    })
    public ResponseEntity<List<TagDto>> getAllTagsSearch(@RequestParam("tagName") String word) {
        return ResponseEntity.ok(tagDtoService.findAllTagsSearch(word));
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ApiOperation(value = "Добавление тэга.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг добавлен")
    })
    public ResponseEntity<TagDto> addMainTag(@RequestBody @Valid TagDto tagDto) {
        tagService.persist(tagConverter.dtoToTag(tagDto));
        return ResponseEntity.ok(tagDto);
    }

    @Validated(OnCreate.class)
    @PostMapping("/{mainTagId}")
    @ApiOperation(value = "Добавление тэга-потомка. {mainTagId} - ID тэга-предка")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг добавлен")
    })
    public ResponseEntity<TagDto> addChildTag(@RequestBody @Valid TagDto tagDto,
                                              @PathVariable @NotNull Long mainTagId) {
        tagService.persistChildTag(tagConverter.dtoToTag(tagDto), mainTagId);
        return ResponseEntity.ok(tagDto);
    }

    @Validated(OnUpdate.class)
    @PutMapping
    @ApiOperation(value = "Изменение тэга.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг изменен")
    })
    public ResponseEntity<TagDto> updateTag(@RequestBody @Valid TagDto tagDto) {
        tagService.update(tagConverter.dtoToTag(tagDto));
        return ResponseEntity.ok(tagDto);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Удаление тэга по ID. {Id} - ID тэга")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг удален")
    })
    public ResponseEntity<String> deleteTag(@PathVariable @NotNull Long id) {
        tagService.deleteByKeyCascadeIgnore(id);
        relatedTagService.deleteRelTagsByTagId(id);
        return ResponseEntity.ok().body("Тэг удален");
    }

}
