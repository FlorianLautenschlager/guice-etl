package de.fla.di.etl.loader;

import de.fla.di.etl.api.Loader;
import de.fla.di.etl.dt.RepositoriesDBEntry;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.List;

@Slf4j
public class TopNStaredRepositoriesLoader implements Loader<List<RepositoriesDBEntry>> {

    public void load(List<RepositoriesDBEntry> toLoad) {
        LOGGER.info("Load {} repositories into the db", toLoad.size());

        try {
            Class.forName("org.h2.Driver");
            Connection sqlConnection = DriverManager.getConnection("jdbc:h2:file:./target/db.h2", "root", "");

            for (RepositoriesDBEntry entry : toLoad) {
                PreparedStatement preparedStmt = sqlConnection.prepareStatement("insert into TOP_N_GIT_REPOSITORIES (REPOSITORY_NAME, STARGAZERS_COUNT, PROGRAMMING_LANGUAGE) values (?, ?, ?)");

                preparedStmt.setString(1, entry.getName());
                preparedStmt.setLong(2, entry.getStargazersCount());
                preparedStmt.setString(3, entry.getLanguage());

                preparedStmt.execute();
            }

            //just for tests
            printDatabase(sqlConnection);

            sqlConnection.close();

        } catch (Exception e) {
            LOGGER.error("Exception occurred", e);
        }
    }

    private void printDatabase(Connection sqlConnection) throws SQLException {
        Statement stmt = sqlConnection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from TOP_N_GIT_REPOSITORIES");
        while (rs.next()) {
            LOGGER.info("Repository: " + rs.getString("REPOSITORY_NAME") + " -----> Stargazers: " + rs.getLong("STARGAZERS_COUNT"));
        }
    }
}
