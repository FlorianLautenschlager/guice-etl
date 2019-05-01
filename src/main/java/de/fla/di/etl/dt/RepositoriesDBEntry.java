package de.fla.di.etl.dt;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RepositoriesDBEntry {

    @NonNull
    private String name;
    @NonNull
    private long stargazersCount;
    @NonNull
    private String language;

}
