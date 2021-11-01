package ru.stqa.b28.addressbook.model;

import java.util.Objects;

public class ContactData {

    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String tittle;
    private final String company;
    private final String mail;
    private final String phone;
    private final String bDay;
    private final String bMonth;
    private final String bYear;

    public ContactData(String firstname,
                       String lastname,
                       String nickname,
                       String tittle,
                       String company,
                       String mail,
                       String phone,
                       String bDay,
                       String bMonth,
                       String bYear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.tittle = tittle;
        this.company = company;
        this.mail = mail;
        this.phone = phone;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
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

    public String getCompany() {
        return company;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
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
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
