package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper()
               .createContact(new ContactData("Ivan", "Ivanov", "tester", null, null, null, null, null, null, null));
        }
        List<ContactData> before  = app.getContactHelper().getContactList();
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
        List<ContactData> after  = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}

