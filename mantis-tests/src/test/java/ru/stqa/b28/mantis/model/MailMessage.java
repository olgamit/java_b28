package ru.stqa.b28.mantis.model;

public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
