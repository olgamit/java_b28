package ru.stqa.b28.mantis.tests;

import org.testng.annotations.*;
import ru.stqa.b28.mantis.model.MailMessage;
import ru.stqa.b28.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase{

    @BeforeMethod
    public void start() {
        app.mail().start();
        app.session().login();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        String password = "password_new";
        List<UserData> users = app.user().getUserList();
        UserData user = users.stream().filter((m) -> !m.getAccess().equals("administrator")).findFirst().get();

        app.user().resetUserPassword(user.getEmail());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
        app.session().complete(confirmationLink, password);
        assertTrue(app.newSession().login(user.getLogin(), password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
