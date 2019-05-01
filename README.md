# Source Code of the Lecture about Dependency

The repository contains the source code for the lecture Dependency Injection at the TH Rosenheim.

## Package lecture

The `lecture` package contains classes to demonstrate dependencies.
The two main classes are called `SomeOne` and `SomeOneGuice`.
The `SomeOne` class uses new to resolve the necessary dependencies while the `SomeOneGuice` class uses Google Guice to resolve the dependencies.

## Package etl
The ETL package implements a minimal ETL job that queries the top stargazed repositories via the GitHub API and stores them in an H2 database (initialized using Flyway).
There are two implementations: One plain Java (`ETLHardCoded.java`) and one with Google Guice (`ETLGuice.java`) and a `Runner.java` that can execute them.

### Guice ETL
![ETL-ARCHITECTURE-DI](/ETL-JOB-USING-DI.png?raw=true)

### API
Defines the `Extractor`, `Transformer` and `Loader` interfaces.

### DT
Contains the data classes `Repositories`, `RepositoriesDBEntry` and `TopNRepositories`

### Extractor, Transformer, Loader
Classes to implement the different stages.

- Extractor: `GitHubStarExtractor` that uses a FeignClient `GitHub` to search for top stargazed repositories
- Transformer: `ToSQLDatabaseEntryTransformer` that transforms `TopNRepositories` into `List<RepositoriesDBEntry>`
- Loader: `TopNStaredRepositoriesLoader` loads the `List<RepositoriesDBEntry>` into a H2 database (setup through Flyway) using a prepared statement.

## Execute

```bash
# build everything
mvn clean install

# execute the plain java implementation of the etl job
mvn exec:java -Dexec.args="plain"

# execute the guice implementation of the etl job
mvn exec:java [-Dexec.args="guice"]
```