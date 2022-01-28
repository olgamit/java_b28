package ru.stqa.b28.mantis.model;

public class UserData {

    private int id;
    private String login;
    private String email;
    private String access;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getAccess() {
        return access;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withAccess(String access) {
        this.access = access;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" + "id=" + id + ", login='" + login + '\'' + ", email='" + email + '\'' + ", access='" +
               access + '\'' + '}';
    }
}
