package de.fla.di.etl.dt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Repositories {

    private String name;

    @SerializedName("stargazers_count")
    private long stargazersCount;
    private String language;
    private String url;

}
