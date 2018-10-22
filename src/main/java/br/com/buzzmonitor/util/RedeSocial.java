package br.com.buzzmonitor.util;

public enum RedeSocial {
    twitter ("twitter"),
    facebook ("facebook"),
    instagran ("instagram");

    private final String media;

    RedeSocial(final String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return this.media;
    }
}

