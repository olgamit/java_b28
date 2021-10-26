package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.b28.addressbook.model.ContactData;

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
        new Select(wd.findElement(locator)).selectByVisibleText(option);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
}
