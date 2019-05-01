package de.fla.di.etl.api;

public interface Loader<O> {

    void load(O out);
}
