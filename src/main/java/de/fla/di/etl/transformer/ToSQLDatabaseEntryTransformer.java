package de.fla.di.etl.transformer;

import de.fla.di.etl.api.Transformer;
import de.fla.di.etl.dt.RepositoriesDBEntry;
import de.fla.di.etl.dt.TopNRepositories;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ToSQLDatabaseEntryTransformer implements Transformer<TopNRepositories, List<RepositoriesDBEntry>> {

    public List<RepositoriesDBEntry> transform(TopNRepositories in) {
        LOGGER.info("Transform to database entry");

        return in.getRepositories().stream()
                .map(entry -> new RepositoriesDBEntry(entry.getName(), entry.getStargazersCount(), entry.getLanguage()))
                .collect(Collectors.toList());
    }
}
