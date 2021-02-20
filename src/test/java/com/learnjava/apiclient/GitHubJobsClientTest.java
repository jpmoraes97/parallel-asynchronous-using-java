package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.learnjava.util.LoggerUtil.log;
import static org.junit.Assert.assertTrue;

class GitHubJobsClientTest {

    WebClient webClient = WebClient.create("https://jobs.github.com");
    GitHubJobsClient gitHubJobsClient = new GitHubJobsClient(webClient);

    @Test
    void invokeGithubJobsAPI_withPageNumber() {
        //given
        int page = 1;
        String description = "JavaScript";

        //when
        List<GitHubPosition> gitHubPositions = gitHubJobsClient.invokeGithubJobsAPI_withPageNumber(page, description);
        log("gitHubPositions" + gitHubPositions);

        //then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions.forEach(Assertions::assertNotNull);
    }
}