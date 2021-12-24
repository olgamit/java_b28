package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.b28.addressbook.model.*;

import java.util.*;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void viewContactDetails() {
        click(By.linkText("home page"));
        click(By.xpath("//img[@alt='Details']"));
    }

    public void fillContactInfo(ContactData contactData, boolean isWithGroup) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTittle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getMail());
        type(By.name("email2"), contactData.getMail2());
        type(By.name("email3"), contactData.getMail3());
        setBirthday(contactData.getbDay(), contactData.getbMonth(), contactData.getbYear());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        click(By.xpath("//div[@id='content']/form/input[21]"));

        if (isWithGroup) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() ==1);
                new Select (wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
            else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
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
        entry.findElement(By.xpath(".//img[@title='Edit']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void create(ContactData contactData, Boolean isWithGroup) {
        addNewContact();
        fillContactInfo(contactData, isWithGroup);
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactInfo(contact, false);
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

    public void resetDefaultHomeView() {
        click(By.id("logo"));
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
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allMails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            ContactData contact = new ContactData().withId(id)
                                                   .withFirstname(firstName)
                                                   .withLastname(lastName)
                                                   .withAddress(address)
                                                   .withAllMails(allMails)
                                                   .withAllPhones(allPhones);
            contacts.add(contact);
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String mail = wd.findElement(By.name("email")).getAttribute("value");
        String mail2 = wd.findElement(By.name("email2")).getAttribute("value");
        String mail3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                                .withFirstname(firstname)
                                .withLastname(lastname)
                                .withHomePhone(home)
                                .withMobilePhone(mobile)
                                .withWorkPhone(work)
                                .withAddress(address)
                                .withMail(mail)
                                .withMail2(mail2)
                                .withMail3(mail3);
    }

    public void addToGroup(ContactData contact, GroupData group) {
        resetDefaultHomeView();
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
        click(By.name("add"));
        returnToHomePage();
    }

    public void addToGroup(ContactData contact, int id) {
        resetDefaultHomeView();
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(id));
        click(By.name("add"));
        returnToHomePage();
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        resetDefaultHomeView();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
        selectContactById(contact.getId());
        click(By.name("remove"));
    }
}
