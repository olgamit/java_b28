package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void createContact(ContactData contactData) {
        addNewContact();
        fillContactInfo(contactData);
        returnToHomePage();

    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        //List<WebElement> elements = wd.findElements(By.cssSelector(" td:nth-child(2)"));
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElement(By.cssSelector(" td:nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector(" td:nth-child(3)")).getText();
            ContactData contact = new ContactData(id,
                                                  firstname,
                                                  lastName,
                                                  null,
                                                  null,
                                                  null,
                                                  null,
                                                  null,
                                                  null,
                                                  null,
                                                  null);
            contacts.add(contact);
        }
        return contacts;
    }
}
