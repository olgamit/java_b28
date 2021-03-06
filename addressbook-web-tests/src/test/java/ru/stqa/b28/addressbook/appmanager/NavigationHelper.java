package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Groups") &&
            isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void gotoDefaultHomeView() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.id("logo"));
    }
}
