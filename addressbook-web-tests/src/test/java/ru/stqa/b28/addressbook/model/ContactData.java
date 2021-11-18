package ru.stqa.b28.addressbook.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String nickname;
    @Expose
    private String company;
    @Expose
    private String tittle;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    @Expose
    private String allPhones;
    @Expose
    private String mail;
    @Expose
    private String mail2;
    @Expose
    private String mail3;
    @Expose
    private String allMails;
    @Expose
    private String bDay;
    @Expose
    private String bMonth;
    @Expose
    private String bYear;


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTittle() {
        return tittle;
    }

    public String getAddress() { return address; }

    public String getCompany() {
        return company;
    }

    public String getMail() {
        return mail;
    }

    public String getMail2() { return mail2; }

    public String getMail3() { return mail3; }

    public String getAllMails() { return allMails; }

    public String getHomePhone() { return homePhone; }

    public String getWorkPhone() { return workPhone; }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getbDay() {
        return bDay;
    }

    public String getbMonth() {
        return bMonth;
    }

    public String getbYear() {
        return bYear;
    }

    public String getAllPhones() { return allPhones; }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public ContactData withMail2(String mail2) {
        this.mail2 = mail2;
        return this;
    }

    public ContactData withMail3(String mail3) {
        this.mail3 = mail3;
        return this;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withMobilePhone(String phone) {
        this.mobilePhone = phone;
        return this;
    }

    public ContactData withBDay(String bDay) {
        this.bDay = bDay;
        return this;
    }

    public ContactData withBMonth(String bMonth) {
        this.bMonth = bMonth;
        return this;
    }

    public ContactData withBYear(String bYear) {
        this.bYear = bYear;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" + "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
