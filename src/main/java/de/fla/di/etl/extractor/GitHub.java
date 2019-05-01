package de.fla.di.etl.extractor;

import de.fla.di.etl.dt.TopNRepositories;
import feign.Param;
import feign.RequestLine;

interface GitHub {
    @RequestLine("GET search/repositories?q=stars:>0&sort=stars&per_page={topN}")
    TopNRepositories topNStarredProjects(@Param("topN") int topN);
}

