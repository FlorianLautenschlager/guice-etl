package de.fla.di.etl;

import com.google.inject.Inject;
import de.fla.di.etl.api.Extractor;
import de.fla.di.etl.api.Loader;
import de.fla.di.etl.api.Transformer;
import de.fla.di.etl.dt.RepositoriesDBEntry;
import de.fla.di.etl.dt.TopNRepositories;

import java.util.List;

class ETLUsingGuice {

    private Extractor<TopNRepositories> extractor;
    private Transformer<TopNRepositories, List<RepositoriesDBEntry>> transformer;
    private Loader<List<RepositoriesDBEntry>> loader;

    @Inject
    ETLUsingGuice(Extractor<TopNRepositories> extractor, Transformer<TopNRepositories, List<RepositoriesDBEntry>> transformer, Loader<List<RepositoriesDBEntry>> loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }

    void run() {
        this.loader.load(this.transformer.transform(this.extractor.extract()));
    }

}
