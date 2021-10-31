package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper()
               .createContact(new ContactData("Ivan", "Ivanov", "tester", null, null, null, null, null, null, null));
        }
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
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}

