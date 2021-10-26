package ru.stqa.b28.addressbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {this.wd = wd;}

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
