package com.mmazurovsky.redcarecase.controller;

import com.mmazurovsky.redcarecase.dto.in.RepositoriesSearchIn;
import com.mmazurovsky.redcarecase.dto.out.RepositoriesSearchOut;
import com.mmazurovsky.redcarecase.service.SearchAndScoringService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RepositoriesSearchController {

    private final SearchAndScoringService searchAndScoringService;

    public RepositoriesSearchController(SearchAndScoringService searchAndScoringService) {
        this.searchAndScoringService = searchAndScoringService;
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<RepositoriesSearchOut>> searchRepositories(
            @Validated @ModelAttribute RepositoriesSearchIn request
    ) {
        return searchAndScoringService.searchAndOutputRepositoriesWithScores(request).collectList();
    }
}