package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
}
