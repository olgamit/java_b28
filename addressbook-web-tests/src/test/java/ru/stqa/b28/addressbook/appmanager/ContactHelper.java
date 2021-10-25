package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.b28.addressbook.model.ContactData;

public class ContactHelper {

    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void viewContactDetails() {
        wd.findElement(By.linkText("home page")).click();
        wd.findElement(By.xpath("//img[@alt='Details']")).click();
    }

    public void fillContactInfo(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wd.findElement(By.name("title")).sendKeys(contactData.getTittle());
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("email")).sendKeys(contactData.getMail());
        setBirthday(contactData.getbDay(), contactData.getbMonth(), contactData.getbYear());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getPhone());
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void setBirthday(String bDay, String bMonth, String bYear) {
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(bDay);
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(bMonth);
        wd.findElement(By.name("byear")).sendKeys(bYear);
    }

    public void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }
}
