package com.mmazurovsky.githubreposobserver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mmazurovsky.githubreposobserver.dto.GithubRepositorySearchResults;
import com.mmazurovsky.githubreposobserver.dto.in.RepositoriesSearchIn;
import com.mmazurovsky.githubreposobserver.dto.out.RepositoriesSearchOut;

@Service
public class SearchAndScoringServiceImpl implements SearchAndScoringService {
    private final SearchService searchService;
    private final ScoringService scoringService;

    public SearchAndScoringServiceImpl(SearchService searchService, ScoringService scoringService) {
        this.searchService = searchService;
        this.scoringService = scoringService;
    }

    @Override
    public List<RepositoriesSearchOut> searchAndOutputRepositoriesWithScores(RepositoriesSearchIn request) {
        final GithubRepositorySearchResults searchResults = searchService.searchRepositories(request);
        return scoringService.convertAndEnrichWithScoreMany(searchResults);
    }
}
