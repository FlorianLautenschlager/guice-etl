package de.fla.di.etl;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import de.fla.di.etl.api.Extractor;
import de.fla.di.etl.api.Loader;
import de.fla.di.etl.api.Transformer;
import de.fla.di.etl.dt.RepositoriesDBEntry;
import de.fla.di.etl.dt.TopNRepositories;
import de.fla.di.etl.extractor.GitHubStarExtractor;
import de.fla.di.etl.loader.TopNStaredRepositoriesLoader;
import de.fla.di.etl.transformer.ToSQLDatabaseEntryTransformer;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import java.util.List;

@Slf4j
public class Runner {

    private static void setup() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:file:./target/db.h2", "root", null)
                .load();
        flyway.migrate();
    }

    public static void main(String[] args) {

        LOGGER.info("Call arguments: {}", (Object[]) args);

        String mode = "guice";
        if (args.length >= 1) {
            mode = args[0];
        }

        setup();

        if ("guice".equalsIgnoreCase(mode)) {
            LOGGER.info("===== ETL USING GUICE =====");

            //TODO: Instantiate the guice injector
            Injector injector = Guice.createInjector(new ETLModule());

            //TODO: Get an Instance of our ETLUsingGuice class
            ETLUsingGuice eTLUsingGuice = injector.getInstance(ETLUsingGuice.class);


            //TODO: Run the ETL job
            eTLUsingGuice.run();
        } else {
            LOGGER.info("===== ETL USING PLAIN JAVA =====");
            new ETLHardCoded().run();
        }
    }

    static class ETLModule extends AbstractModule {
        @Override
        protected void configure() {

            //We need to use TypeLiteral in order to use generics

            //Bind extractor
            bind(new TypeLiteral<Extractor<TopNRepositories>>() {})
                    .to(GitHubStarExtractor.class);

            //Bind transformer
            bind(new TypeLiteral<Transformer<TopNRepositories, List<RepositoriesDBEntry>>>() {})
                    .to(ToSQLDatabaseEntryTransformer.class);

            //Bind loader
            bind(new TypeLiteral<Loader<List<RepositoriesDBEntry>>>() {})
                    .to(TopNStaredRepositoriesLoader.class);
        }
    }
}
