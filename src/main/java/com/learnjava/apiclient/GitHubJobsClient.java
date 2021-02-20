package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

public class GitHubJobsClient {

    private final WebClient webClient;

    public GitHubJobsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_withPageNumber(int page, String description) {
        String uri = UriComponentsBuilder.fromUriString("/positions.json")
                .queryParam("description", description)
                .queryParam("page", page)
                .buildAndExpand()
                .toUriString();
        log("URI IS: " + uri);
        List<GitHubPosition> gitHubPostitions = webClient.get().uri(uri)
                .retrieve()
                .bodyToFlux(GitHubPosition.class)
                .collectList()
                .block();
        return gitHubPostitions;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_usingMultiplePageNumbers(List<Integer> pageNumbers, String description) {

        startTimer();
        List<GitHubPosition> gitHubPostitions = pageNumbers.stream()
                .map(page -> invokeGithubJobsAPI_withPageNumber(page, description))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        timeTaken();
        return gitHubPostitions;
    }

}
