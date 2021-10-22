package ru.stqa.b28.addressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
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

    protected void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    protected void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
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

    protected void deleteSelectedGroups() {
        wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }

    protected void viewContactDetails() {
        wd.findElement(By.linkText("home page")).click();
        wd.findElement(By.xpath("//img[@alt='Details']")).click();
    }

    protected void fillContactInfo(ContactData contactData) {
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

    private void setBirthday(String bDay, String bMonth, String bYear) {
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(bDay);
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(bMonth);
        wd.findElement(By.name("byear")).sendKeys(bYear);
    }

    protected void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }
}
