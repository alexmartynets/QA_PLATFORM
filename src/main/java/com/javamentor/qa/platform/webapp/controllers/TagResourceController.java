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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping(value = "/api/user/tag", produces = "application/json")
@Api(value="TagApi", description = "Операции с тэгами (создание, изменение, получение списков с сортировками, поиск по имени тэга, удаление)")
public class TagResourceController {

    private final TagDtoService tagDtoService;
    private final TagService tagService;
    private final RelatedTagService relatedTagService;
    private final TagConverter tagConverter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TagResourceController(TagDtoService tagDtoService, TagService tagService, RelatedTagService relatedTagService, TagConverter tagConverter) {
        this.tagDtoService = tagDtoService;
        this.tagService = tagService;
        this.relatedTagService = relatedTagService;
        this.tagConverter = tagConverter;
    }

    @GetMapping
    @ApiOperation(value = "Получение количества тэгов и списка тэгов на странице отсортированных по популярности. " +
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
    @ApiOperation(value = "Получение количества тэгов и списка тэгов на странице отсортированных по имени. " +
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
    @ApiOperation(value = "Получение количества всех тэгов в БД и списка тэгов на странице отсортированных по дате добавления. " +
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
    @ApiOperation(value = "Получение количества всех тэгов в БД и списка тэгов на странице по заданномму в параметре имени, отсортированных по популярности. " +
            "Параметры запроса: " +
            "'tagName'- имя или часть имени искомого тэга" +
            "'pageSize'- количество элементов на странице и " +
            "'pageNumber' номер страницы")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список тэгов получен")
    })
    public ResponseEntity<Pair<Long, List<TagDto>>> getAllTagsSearch(@RequestParam("tagName") String word,
                                                         @RequestParam("pageSize") int pageSize,
                                                         @RequestParam("pageNumber") int pageNumber) {
        return ResponseEntity.ok(tagDtoService.findAllTagsSearch(word, pageSize, pageNumber));
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ApiOperation(value = "Добавление тэга.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг добавлен")
    })
    public ResponseEntity<TagDto> addMainTag(@RequestBody @Valid TagDto tagDto) {
        tagService.persist(tagConverter.dtoToTag(tagDto));
        logger.info(String.format("Тэг: %s добавлен в базу данных", tagDto.getName()));
        return ResponseEntity.ok(tagDto);
    }

    @Validated(OnCreate.class)
    @PostMapping("/{mainTagId}")
    @ApiOperation(value = "Добавление тэга-потомка. {mainTagId} - ID тэга-предка")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг добавлен.")
    })
    public ResponseEntity<TagDto> addChildTag(@RequestBody @Valid TagDto tagDto,
                                              @PathVariable @NotNull Long mainTagId) {
        tagService.persistChildTag(tagConverter.dtoToTag(tagDto), mainTagId);
        logger.info(String.format("Тэг: %s добавлен в базу данных.", tagDto.getName()));
        return ResponseEntity.ok(tagDto);
    }

    @Validated(OnUpdate.class)
    @PutMapping
    @ApiOperation(value = "Изменение тэга.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Тэг изменен.")
    })
    public ResponseEntity<TagDto> updateTag(@RequestBody @Valid TagDto tagDto) {
        tagService.update(tagConverter.dtoToTag(tagDto));
        logger.info(String.format("Тэг id: %s name: %s изменен.",tagDto.getId(), tagDto.getName()));
        return ResponseEntity.ok(tagDto);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Удаление тэга по ID. {Id} - ID тэга")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Тэг удален")
    })
    public ResponseEntity<String> deleteTag(@PathVariable @NotNull Long id) {
        tagService.deleteByKeyCascadeIgnore(id);
        relatedTagService.deleteRelTagsByTagId(id);
        logger.info(String.format("Тэг id: %s удален", id));
        return ResponseEntity.accepted().body("Тэг удален");
    }

    @GetMapping("/mainTags")
    @ApiOperation(value = "Получаем тэги отсортированные по частоте упоминания за все время")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список отсортированных тэгов получен")})

    public ResponseEntity<List<TagDto>> getAllMainTagsSortedByFrequency() {
        return ResponseEntity.ok(tagDtoService.getAllMainTagsSortedByFrequency());
    }

    @GetMapping("/relatedTags/{mainTagId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список связанных тэгов получен")})
    @ApiOperation(value = "Получаем связанные тэги также отсортированные по частоте упоминания")
    public ResponseEntity<List<TagDto>> getRelatedTags(@PathVariable Long mainTagId) {
        List<TagDto> abc = tagDtoService.getRelatedTags(mainTagId);
        return ResponseEntity.ok(tagDtoService.getRelatedTags(mainTagId));
    }
}
