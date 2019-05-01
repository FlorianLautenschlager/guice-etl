package de.fla.di.etl.api;

public interface Transformer<I, O> {

    O transform(I in);
}
