package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.b28.addressbook.model.ContactData;
import ru.stqa.b28.addressbook.model.Contacts;

import java.util.*;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void viewContactDetails() {
        click(By.linkText("home page"));
        click(By.xpath("//img[@alt='Details']"));
    }

    public void fillContactInfo(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTittle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("email"), contactData.getMail());
        setBirthday(contactData.getbDay(), contactData.getbMonth(), contactData.getbYear());
        type(By.name("mobile"), contactData.getPhone());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void setBirthday(String bDay, String bMonth, String bYear) {
        selectDropDown(bDay, By.name("bday"));
        selectDropDown(bMonth, By.name("bmonth"));
        type(By.name("byear"), bYear);
    }

    private void selectDropDown(String option, By locator) {
        if (option != null) {
            String selectedValue = new Select(wd.findElement(locator)).getFirstSelectedOption().getText();
            if (! option.equals(selectedValue)) {
                if (isElementPresent(locator)) {
                    new Select(wd.findElement(locator)).selectByVisibleText(option);
                }
            }
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        WebElement entry = wd.findElement(By.xpath("//input[@id= '" + id + "']/../.."));
        entry.findElement(By.xpath("//img[@title='Edit']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void create(ContactData contactData) {
        addNewContact();
        fillContactInfo(contactData);
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactInfo(contact);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        returnToHomePage();
    }


    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElement(By.cssSelector(" td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector(" td:nth-child(3)")).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}
