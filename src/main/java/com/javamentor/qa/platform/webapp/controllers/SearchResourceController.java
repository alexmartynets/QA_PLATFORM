package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.service.abstracts.dto.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user/search")
public class SearchResourceController {

    private final SearchService searchService;

    @Autowired
    public SearchResourceController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<?> search(@RequestParam(name = "search") String searchRequest) {
        return searchService.search(searchRequest);
    }
}