package ru.stqa.b28.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b28.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isContactExist()) {
            app.getContactHelper()
               .createContact(new ContactData("Ivan", "Ivanov", "tester", null, null, null, null, null, null, null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                                              "Petr",
                                              "Petrov",
                                              "dev",
                                              "developer",
                                              "IT company",
                                              "devuser@test.com",
                                              "8 923 444 5566",
                                              "3",
                                              "March",
                                              "2001");
        app.getContactHelper().fillContactInfo(contact);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        final Comparator<? super ContactData> byID = Comparator.comparingInt(ContactData::getId);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }
}

