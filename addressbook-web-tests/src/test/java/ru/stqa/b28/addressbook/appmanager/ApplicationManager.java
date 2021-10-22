package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.b28.addressbook.model.ContactData;
import ru.stqa.b28.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    public void login(String username, String password) {
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.id("content")).click();
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void deleteSelectedGroups() {
        wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
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
