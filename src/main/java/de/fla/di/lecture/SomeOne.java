package de.fla.di.lecture;

class SomeOne {

    private NeedsSomeBody needsSomeBody;

    private SomeOne() {
        this.needsSomeBody = new ToLove();
    }

    private void needsSomeBodyToLove() {
        this.needsSomeBody.toLove();
    }

    public static void main(String[] args) {
        new SomeOne().needsSomeBodyToLove();
    }
}

