package ru.stqa.b28.mantis.tests;

import org.testng.annotations.*;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.b28.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() { app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis() / 1000 / 60;
        String username = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);

        app.session().signup(username, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);

        app.session().complete(confirmationLink, password);
        assertTrue(app.newSession().login(username, password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
