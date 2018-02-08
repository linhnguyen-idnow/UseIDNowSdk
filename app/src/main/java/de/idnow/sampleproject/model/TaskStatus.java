package de.idnow.sampleproject.model;

/**
 * Created by sean on 1/8/18.
 */

public enum TaskStatus {
    TODO ("To do"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private String text;

    TaskStatus(String text) {
        this.text = text;
    }

    public String getStatus() {
        return text;
    }
}
