package ru.stqa.b28.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.b28.mantis.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public List<UserData> getUserList() {
        manageUsers();
        List<UserData> users = new ArrayList<>();
        List<WebElement> elements = wd.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String href = cells.get(0).findElement(By.cssSelector("a")).getAttribute("href");
            int id = Integer.parseInt(href.substring(href.indexOf("=") + 1));
            String login = cells.get(0).getText();
            String email = cells.get(2).getText();
            String access = cells.get(3).getText();

            users.add(new UserData().withId(id).withLogin(login).withEmail(email).withAccess(access));
        }
        return users;
    }

    public void manageUsers() {
        wd.findElement(By.cssSelector("a.manage-menu-link")).click();
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void selectUserByEmail(String email) {
        List<WebElement> userRows = wd.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for (WebElement user : userRows) {
            List<WebElement> cells = user.findElements(By.tagName("td"));
            WebElement link = cells.get(0).findElement(By.cssSelector("a"));
            if (cells.get(2).getText().equals(email)) {
                link.click();
                break;
            }
        }
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));

    }

    public void resetUserPassword(String email) {
        manageUsers();
        selectUserByEmail(email);
        resetPassword();
    }


}
