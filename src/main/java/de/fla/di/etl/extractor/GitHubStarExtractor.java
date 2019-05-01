package de.fla.di.etl.extractor;

import de.fla.di.etl.api.Extractor;
import de.fla.di.etl.dt.TopNRepositories;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GitHubStarExtractor implements Extractor<TopNRepositories> {

    private final GitHub github;

    public GitHubStarExtractor() {
        this.github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

    }

    public TopNRepositories extract() {
        LOGGER.info("Extract top project from github");
        return github.topNStarredProjects(3);
    }
}
