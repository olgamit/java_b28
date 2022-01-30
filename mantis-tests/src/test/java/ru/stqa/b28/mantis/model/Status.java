package ru.stqa.b28.mantis.model;

public class Status {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Status withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Status withName(String name) {
        this.name = name;
        return this;
    }

}
