package ru.stqa.b28.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper()
           .fillContactInfo(new ContactData("Petr",
                                            "Petrov",
                                            "dev",
                                            "developer",
                                            "IT company",
                                            "devuser@test.com",
                                            "8 923 444 5566",
                                            "3",
                                            "March",
                                            "2001"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}

