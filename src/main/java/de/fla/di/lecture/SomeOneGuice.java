package de.fla.di.lecture;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

class SomeOneGuice {

    private NeedsSomeBody needsSomeBody;

    @Inject
    private SomeOneGuice(NeedsSomeBody needsSomeBody) {
        this.needsSomeBody = needsSomeBody;
    }

    private void needsSomeBodyToLove() {
        this.needsSomeBody.toLove();
    }

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new SomeOneGuiceModule());
        SomeOneGuice someOneGuice = injector.getInstance(SomeOneGuice.class);

        someOneGuice.needsSomeBodyToLove();
    }

    private static class SomeOneGuiceModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(NeedsSomeBody.class).to(ToLove.class);
        }
    }
}

