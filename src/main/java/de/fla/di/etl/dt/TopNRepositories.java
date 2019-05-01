package de.fla.di.etl.dt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class TopNRepositories {

    @SerializedName("total_count")
    private long totalCount;

    @SerializedName("incomplete_results")
    private boolean incomplete;

    @SerializedName("items")
    private List<Repositories> repositories;

}
