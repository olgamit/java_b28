package ru.stqa.b28.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "nickname")
    private String nickname;

    @Expose
    @Column(name = "company")
    private String company;

    @Expose
    @Column(name = "title")
    private String tittle;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String mail;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String mail2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String mail3;

    @Expose
    @Transient
    private String allMails;

    @Expose
    @Transient
    //@Column(name = "bday")
    //@Type(type = "byte")
    private String bDay;

    @Expose
    @Transient
    //@Column(name = "bmonth")
    private String bMonth;

    @Expose
    @Transient
    //@Column(name = "byear")
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
        //if (!bDay.equals(null)) {
        //    return Byte.toString(bDay);
        //} else return "";
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
        //this.bDay = Byte.parseByte(bDay);
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
