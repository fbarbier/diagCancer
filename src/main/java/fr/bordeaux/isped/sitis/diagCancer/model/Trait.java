package fr.bordeaux.isped.sitis.diagCancer.model;

public enum Trait {
    CHECKED("C"), UNCHECKED("U"), DOUBLON("D");

    private String doublon;

    private Trait(String code) {
        this.doublon = code;
    }

    public String getTrait() {
        return doublon;
    }
}
