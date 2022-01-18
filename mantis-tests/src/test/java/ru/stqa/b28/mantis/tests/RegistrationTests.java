package ru.stqa.b28.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("user1", "mail11@localhost.localdomain");
    }
}
