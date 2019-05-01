package de.fla.di.etl;

import de.fla.di.etl.api.Extractor;
import de.fla.di.etl.api.Loader;
import de.fla.di.etl.api.Transformer;
import de.fla.di.etl.dt.RepositoriesDBEntry;
import de.fla.di.etl.dt.TopNRepositories;
import de.fla.di.etl.loader.TopNStaredRepositoriesLoader;
import de.fla.di.etl.extractor.GitHubStarExtractor;
import de.fla.di.etl.transformer.ToSQLDatabaseEntryTransformer;

import java.util.List;

class ETLHardCoded {

    private Extractor<TopNRepositories> extractor;
    private Transformer<TopNRepositories, List<RepositoriesDBEntry>> transformer;
    private Loader<List<RepositoriesDBEntry>> loader;

    ETLHardCoded() {
        this.extractor = new GitHubStarExtractor();
        this.transformer = new ToSQLDatabaseEntryTransformer();
        this.loader = new TopNStaredRepositoriesLoader();
    }

    void run() {
        this.loader.load(this.transformer.transform(this.extractor.extract()));
    }
}
